package com.tenjishen.common.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * Lucene入门案例
 * 
 * @author TENJI
 * @since
 * @date 2014-9-2
 */
public class Test20140901 {

	public static void main(String[] args) {
		try {
			/*
			 * 分析器，主要用于分析搜索引擎遇到的各种文本。
			 * 常用的有StandardAnalyzer分析器，StopAnalyzer分析器和WhitespaceAnalyzer分析器等。
			 * StandardAnalyzer是Lucene中内置的“标准分析器”。
			 */
			StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
			
			Directory directory = new RAMDirectory(); // Store the index in momory
			// To store an index on disk, use this instead below:
			// Directory directory = FSDirectory.open("/tmp/testindex");
			
			/*
			 * IndexWriter是Lucene中最重要的类之一，它主要是用来将文档加入索引，同时控制
			 * 索引过程中一些参数使用。
			 */
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LUCENE_CURRENT, analyzer);
			IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

			/*
			 * Document相当于一个想要进行索引的单元，任何可以想要被索引的文件都必须转化为Document
			 * 对象才能进行索引。
			 */
			Document document = new Document();
			String text = "This is the text to indexed, to indexed again!";
			document.add(new Field("fieldname", text, TextField.TYPE_STORED));
			
			indexWriter.addDocument(document);
			indexWriter.close();

			
			
			// Now search the index:
			DirectoryReader ireader = DirectoryReader.open(directory);
			
			/*
			 * IndexSearcher是Lucene中最基本的检索工具，所有的检索都会用到IndexSearcher工具。
			 */
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			// Parse a simple query that searches for "text":
			/*
			 * QueryParser是一个解析用户输入的工具，可以通过扫描用户输入的字符串，生成Query对象。
			 */
			QueryParser parser = new QueryParser(Version.LUCENE_CURRENT,
					"fieldname", analyzer);
			/*
			 * Query查询，Lucene中支持模糊查询，语义查询，短语查询，组合查询等等。
			 * 比如有TermQuery, BooleanQuery, RangeQuery, WildcardQuery等一些类。
			 */
			Query query = parser.parse("text");
			
			ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
			System.out.println("Length of Hits: " + hits.length);
			
			// Iterate through the results:
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println("This is the text to be indexed: " + hitDoc.get("fieldname"));
			}
			ireader.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
