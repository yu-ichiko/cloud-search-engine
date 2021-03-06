//---------------------------------------------------------
//Crawlerクラス
//
//ファサードパターンで、ファイルの拡張子から最適な方法で文書を抽出し、Solrに格納
//---------------------------------------------------------
package upload;

import java.io.File;
import java.util.Date;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class Crawler {

	//-----------------------------------------------------
	//プロパティ
	//-----------------------------------------------------

	//ファイルパス
	private static String filePath;
	//インデックスを格納するサーバ
	private static String server;
	//アカウント情報格納
	private static String account;

	//-----------------------------------------------------
	//コンストラクタ
	//-----------------------------------------------------

	/**
	 * コンストラクタ
	 *
	 * @param account
	 * @param path
	 * @param server
	 */
	public Crawler(String account, String path, String server) {
		Crawler.setAccount(account);
		Crawler.setFilePath(path);
		Crawler.setServer(server);
	}

	//-----------------------------------------------------
	//get・setメソッド
	//-----------------------------------------------------

	/**
	 * setAccountメソッド
	 *
	 * @param account
	 */
	public static void setAccount(String account) {
		Crawler.account = account;
	}

	/**
	 * getAccountメソッド
	 *
	 * @return
	 */
	public static String getAccount() {
		return account;
	}

	/**
	 * setFilePathメソッド
	 *
	 * @param filePath
	 */
	public static void setFilePath(String filePath) {
		Crawler.filePath = filePath;
	}

	/**
	 * getFilePathメソッド
	 *
	 * @return
	 */
	public static String getFilePath() {
		return filePath;
	}

	/**
	 * setServerメソッド
	 *
	 * @param server
	 */
	public static void setServer(String server) {
		Crawler.server = server;
	}

	/**
	 * getServerメソッド
	 *
	 * @return
	 */
	public static String getServer() {
		return server;
	}

	//-----------------------------------------------------
	//publicメソッド
	//-----------------------------------------------------

	/**
	 * setIndexメソッド
	 *
	 * @return
	 */
	public boolean setIndex() {
		try {

			//拡張子
			String suffix = getSuffix(filePath);

			//インデックスを格納するサーバを決める
			SolrServer solr = new CommonsHttpSolrServer(server);

			//ドキュメントを作成
			SolrInputDocument document = new SolrInputDocument();
			//フィールドの指定
			document.addField("id", Crawler.filePath);
			document.addField("account", Crawler.account);

			File file = new File(filePath);

			//document.addField("title", file.getName());
			//document.addField("type", suffix);
			//document.addField("path", file.getPath());
			//document.addField("date", new Date(file.lastModified()));
			//document.addField("time", file.lastModified());

			System.out.println(new Date(file.lastModified()));
			System.out.println(file.getPath());
			System.out.println(file.length());

			//拡張子により読み込み処理を変える
			if (suffix.equals("txt")) {
				TextFileReader reader = new TextFileReader();
				document = reader.extractText(filePath, document);
			} else if (suffix.equals("doc")) {
				WordReader reader = new WordReader();
				document = reader.extractDoc(filePath, document);
			} else if (suffix.equals("pdf")) {
				PDFReader reader = new PDFReader();
				document = reader.extractPDF(filePath, document);
			} else if (suffix.equals("ppt")) {
				PowerPointReader reader = new PowerPointReader();
				document = reader.extractPPT(filePath, document);
			}

			//サーバに追加
			solr.add(document);
			//コミット
			solr.commit();
			//最適化
			solr.optimize();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//-----------------------------------------------------
	//内部メソッド
	//-----------------------------------------------------

	/**
	 * getSuffixメソッド (拡張子を調べる)
	 *
	 * @param fileName
	 * @return
	 */
	private static String getSuffix(String fileName) {
		if (fileName == null) {
			return null;
		}

		int point = fileName.lastIndexOf(".");
		if (point != -1) {
			return fileName.substring(point + 1);
		}
		return fileName;
	}

}
