package com.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.SortedNumericDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ioc.test.User;

/**
 * @date 2017年11月21日上午11:08:56
 * @Description:
 * @authorAdministrator
 */
public class FirstLucene {
	
	
	
	public Te<Double> t;
	public Te<Double> getT() {
		return t;
	}
	
	public void setT(Te<Double> t) {
		this.t = t;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public IndexWriter getIndexWriter() throws Exception {
		Directory directory = FSDirectory.open(new File("E:/a").toPath());
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		return indexWriter;
	}

	public IndexReader getIndexReader() throws IOException {
		// 1.创建一个Directory对象，表示存放索引的位置
		FSDirectory directory = FSDirectory.open(new File("E:/a").toPath());
		// 2.创建一个indexReader对象，需要指定directory对象
		IndexReader indexReader = DirectoryReader.open(directory);
		return indexReader;
	}

	public void printResult(IndexSearcher indexSearcher, Query query) throws IOException {
		// 5.执行查询
		TopDocs topDocs = indexSearcher.search(query, 5);
		// 6.返回查询结果，遍历查询结果并输出
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int doc = scoreDoc.doc;
			Document document = indexSearcher.doc(doc);
			String username = document.get("username");
			String age = document.get("age");
			String text = document.get("text");
			String fileN = document.get("fileN");
			System.out.println(username + "," + age + "," + text + "," + fileN);
		}
	}

	@Test
	public void testCreateIndex() throws Exception {
		// 1.创建一个indexwritter对象 (指定索引库的位置,.指定一个分析器)
		IndexWriter indexWriter = getIndexWriter();

		User user0 = new User(12, "aa", "efrw2342");
		User user1 = new User(13, "ab", "fsd2425");
		User user2 = new User(14, "ac", "234ve24");
		List<User> list = new ArrayList<>();
		list.add(user0);
		list.add(user1);
		list.add(user2);
		for (User user : list) {
			// 2.创建一个document对象
			Document document = new Document();
			int age = user.getAge();
			NumericDocValuesField ageField = new NumericDocValuesField("age", age);
			String username = user.getName();
			TextField nameField = new TextField("username", username, Store.YES);
			String text = user.getText();
			TextField textField = new TextField("text", text, Store.YES);
			// 3.创建field对象，并且添加到document中
			document.add(ageField);
			document.add(nameField);
			document.add(textField);
			// 4.使用Indexwritter对象将document对象写入索引库
			indexWriter.addDocument(document);
			//
		}
		indexWriter.close();
	}

	@Test
	public void testQueryIndex() throws Exception {
		/*
		 * // 1.创建一个Directory对象，表示存放索引的位置 FSDirectory directory =
		 * FSDirectory.open(new File("E:/a").toPath()); //
		 * 2.创建一个indexReader对象，需要指定directory对象 IndexReader indexReader =
		 * DirectoryReader.open(directory);
		 */
		IndexReader indexReader = getIndexReader();
		// 3.创建一个indexSearch对象，需要指定IndexReader对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 4.创建一个TermQuery对象，指定查询的域和查询的关键词
		Query query = new TermQuery(new Term("username", "aa"));
		// 5.执行查询
		// 6.返回查询结果，遍历查询结果并输出
		printResult(indexSearcher, query);
		// 7.关闭indexReader对象
		indexReader.close();

	}

	@Test
	public void testQueryAll() throws Exception {
		IndexReader indexReader = getIndexReader();
		// 3.创建一个indexSearch对象，需要指定IndexReader对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 4.创建一个MatchAllDocsQuery对象，就表示查询所有。
		Query query = new MatchAllDocsQuery();
		// 5.执行查询
		// 6.返回查询结果，遍历查询结果并输出
		printResult(indexSearcher, query);
		// 7.关闭 indexReader对象
		indexReader.close();
	}
	@Test
	public void testQueryRange() throws Exception {
		IndexReader indexReader = getIndexReader();
		// 3.创建一个indexSearch对象，需要指定IndexReader对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 4.创建一个Query对象，就表示查询某个范围。
		Query query = SortedNumericDocValuesField.newSlowRangeQuery("age", 0, 100);
		// 5.执行查询
		// 6.返回查询结果，遍历查询结果并输出
		printResult(indexSearcher, query);
		// 7.关闭 indexReader对象
		indexReader.close();
	}
	@Test
	public void testQueryGroup() throws Exception {
		 Analyzer analyzer = new IKAnalyzer();//IK分析器
		IndexReader indexReader = getIndexReader();
		// 3.创建一个indexSearch对象，需要指定IndexReader对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 4.创建两个query对象。
		Query query1 = new TermQuery(new Term("username", "aa"));
		//查询username包含java或者apache的。
		Query query2 = new QueryParser( "text", analyzer ).parse("java is  a  apache");
		//下面这种写法，默认的搜索域（text）就不起作用了。
//		Query query2 = new QueryParser( "text", analyzer ).parse("fileName:apache");
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		 builder.add( query1, Occur.MUST);
		 builder.add( query2, Occur.MUST);
		// 5.执行查询(username必须为aa，而且text必须为qer)
		// 6.返回查询结果，遍历查询结果并输出
		printResult(indexSearcher, builder.build());
		// 7.关闭 indexReader对象
		indexReader.close();
	}
	@Test
	public void testDelete() throws Exception {
		IndexWriter indexWriter = getIndexWriter();
		Query query = new TermQuery(new Term("username", "aa"));
		indexWriter.deleteDocuments(query);
		indexWriter.close();
	}

	@Test
	public void testUpdate() throws Exception {
		IndexWriter indexWriter = getIndexWriter();
		Document doc = new Document();
		doc.add(new TextField("fileN", "qer", Store.YES));
		indexWriter.updateDocument(new Term("username", "aa"), doc);
		indexWriter.close();
	}
}
