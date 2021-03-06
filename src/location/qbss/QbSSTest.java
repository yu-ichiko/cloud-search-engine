package location.qbss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QbSSTest {

	/**
	 * mainメソッド
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		//クエリーの設定
		String query = "456 AND 456 NOT ***";
		//String query = "\"aaa bbb\"";
		//Cassandraの結果集合
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> list1 = new ArrayList<String>();
		list1.add("http://localhost:8983/solr/");
		list1.add("http://localhost:7574/solr/");
		map.put("456", list1);
		List<String> list2 = new ArrayList<String>();
		list2.add("http://localhost:8983/solr/");
		map.put("大島", list2);
		List<String> list3 = new ArrayList<String>();
		list3.add("http://localhost:6564/solr/");
		list3.add("http://localhost:9684/solr/");
		list3.add("http://localhost:8983/solr/");
		map.put("***", list3);
		List<String> list4 = new ArrayList<String>();
		list4.add("http://localhost:6564/solr/");
		map.put("dDd", list4);

		//System.out.println(map);

		//QbSS
		QbSS qbss = new QbSS(query, map);
		System.out.println("QbSSTest: " + qbss.parser());

	}

}
