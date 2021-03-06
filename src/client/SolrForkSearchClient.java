//---------------------------------------------------------
//SolrJSearchClient クラス
//
//SolrJを利用して検索をする(最終的にはServletにする)
//---------------------------------------------------------
package client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import location.Location;
import location.query.QueryConverter;

import org.apache.solr.common.SolrDocument;

import client.config.XMLConfig;

import analysis.SenAnalyzerExtract;

import solr.fork.SolrFork;
import solr.ranking.DistributedSimilarity;
import upload.consistency.ConsistentHashing;

public class SolrForkSearchClient {

	/**
	 * Cloud-Search-Engineの検索部分
	 * 		1.ユーザーがGoogle形式のクエリーを入力
	 * 		2.クエリー内容をインデックスで作成されたタームの形にする
	 * 		3.LocationにアクセスしQbSSにより最適なアクセス先を探す
	 * 		4.分散検索をする
	 * 		5.修正したランキング順に結果を表示する
	 *
	 *
	 * @param args
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		//GSEのConfig.xmlデータを取得する
		XMLConfig config = new XMLConfig("demo/gse-config.xml");

		//Location Serverのアドレスとポートを取得する
		Map<String, String> locationConfig = config.getHost2Port("location");
		String locationHost = locationConfig.get("host");
		int locationPort = Integer.valueOf(locationConfig.get("port"));

		//ユーザーからのクエリー
		String queryString = "芥川";

		//ユーザーのアカウント情報
		String account = "demo1";

		//検索数
		int rows = 10;

		//クエリーの解析
		QueryConverter queryConverter = new QueryConverter();
		queryConverter.parser(queryString);

		//データベース(Locationサーバ)にアクセス
		Location location = new Location(locationHost, locationPort);
		//正規化したクエリーを与える
		location.query(queryConverter.getQuery(), "sen");
		//CJKAnalyzerでタームを分割してLocationに与えるデータを作る
		SenAnalyzerExtract analyzerExtract = new SenAnalyzerExtract(queryConverter.getTermList());
		//クエリーのタームを与える
		Map<String, Object> map = location.get(analyzerExtract.extract());

		//Location ServerからNodesリストを取得する
		Map<String, String> nodes = location.getNodes();
		//分散ルール
		ConsistentHashing hash = new ConsistentHashing();
		//停止ノードリスト
		List<String> fault = new ArrayList<String>();
		for (Iterator<String> it = nodes.keySet().iterator(); it.hasNext();) {
			String id = it.next();
			hash.addNode(id);
			//停止しているノードを登録する
			if (nodes.get(id).equals("fault")) {
				fault.add(id);
			}
		}

		//URL作成
		List<String> urlList = new ArrayList<String>();
		for (String node : (List<String>) map.get("url")) {
			if (fault.contains(node)) {
				node = hash.nextNode(node);
				urlList.add("http://" + node + ":8983/solr/core1/");
			} else {
				urlList.add("http://" + node + ":8983/solr/core0/");
			}
		}

		//maxDocs
		int maxDocs = (Integer) map.get("maxDocs");

		//docFreq
		Map<String, Integer> docFreq = (Map<String, Integer>) map.get("docFreq");

		//分散検索
		SolrFork server = new SolrFork();

		//アクセス先とキーワード
		server.searchExplain(urlList, "(" + queryConverter.getQuery() + ") AND account:" + account);
		Map<String, String> debugList = server.getExplain();

		//ランキング修正
		DistributedSimilarity ranking = new DistributedSimilarity(docFreq, maxDocs);
		ranking.solrScoreImport(debugList);
		List<Map<String, Object>> documentResult = ranking.ranking();

		//10件以下の場合
		if (documentResult.size() < rows) {
			rows = documentResult.size();
		}

		//上位10件のidの列挙
		String ids = "";
		for (int i = 0; i < rows -1 ; i++) {
			String id = documentResult.get(i).get("id").toString();
			ids +=  "id:" + id + " ";
		}
		ids += "id:" + documentResult.get(rows - 1).get("id").toString();

		//詳細検索
		//アクセス先とidの列挙
		server.searchResponse(urlList, ids);
		Map<String, SolrDocument> doc = server.getResponse();
		//System.out.println(doc);

		//トップ10件の表示
		for (int i = 0; i < rows; i++) {
			System.out.println("---------- " + (i+1) + " ----------");
			System.out.println("score : " + documentResult.get(i).get("score"));
			System.out.println("id : " + documentResult.get(i).get("id"));
			List<String> msg = (List<String>) doc.get(documentResult.get(i).get("id")).get("text");
			System.out.println(msg.get(0) + " " + msg.get(1));
			System.out.println(doc.get(documentResult.get(i).get("id")).get("time"));
			System.out.println(doc.get(documentResult.get(i).get("id")).get("type"));
			System.out.println(doc.get(documentResult.get(i).get("id")).get("title"));
			System.out.println();
		}
	}
}
