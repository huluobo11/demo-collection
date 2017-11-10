package com.solr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @date 2017年10月23日上午10:46:31
 * @Description: TODO
 * @authorAdministrator
 */
public class SolrJTest {
	@Test
	public void testUpdate() throws SolrServerException, IOException {
		SolrServer solrServer = new HttpSolrServer("http://101.200.59.200/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "doc05");
		document.addField("item_title", "oppoR9");
		document.addField("item_sell_point", "5折");
		document.addField("item_price", 88);
		document.addField("item_image", "oppoR9.jpg");
		document.addField("item_category_name", " 分组");
		solrServer.add(document);
		solrServer.commit();
	}
	@Test
	public void testQuery() throws SolrServerException, IOException {
		SolrServer solrServer = new HttpSolrServer("http://101.200.59.200/solr");
		SolrQuery query=new SolrQuery();
		query.setQuery("item_title:标题");
//		query.setQuery("*:*");
		query.set("df", "item_keywords");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		QueryResponse response = solrServer.query(query);
		SolrDocumentList results = response.getResults();
		System.out.println("条数"+results.getNumFound());
		System.out.println();
		for (SolrDocument solrDocument : results) {
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String itemTitle = null;
			if (list != null && list.size() > 0) {
				itemTitle = list.get(0);
			} else {
				itemTitle = (String) solrDocument.get("item_title");
			}
			System.out.println(itemTitle);
			System.out.println(solrDocument.get("item_price"));

		}
	}
}
