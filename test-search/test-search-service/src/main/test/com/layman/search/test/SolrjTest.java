package com.layman.search.test;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLOutput;

public class SolrjTest {

    @Test
    public void add() throws IOException, SolrServerException {
        // 1. 创建solrServer 建立连接需要指定地址
        SolrServer solrServer = new HttpSolrServer("http://118.24.27.161:8080/solr");
        // 2. 创建solrinputdocument
        SolrInputDocument document = new SolrInputDocument();
        // 3. 向文档中添加域
        document.addField("id","test001");
        document.addField("item_title","这是一个测试1");
        // 4. 将文件提交到索引库中
        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void testquery() throws SolrServerException {
        //1 .创建solrServer对象
        SolrServer solrServer = new HttpSolrServer("http://118.24.27.161:8080/solr");
        //2. 创建solrquery对象, 设置各种过滤条件 主查询条件 排序
        SolrQuery solrQuery = new SolrQuery();
        //3. 设置查询的条件
        solrQuery.setQuery("电视");
        solrQuery.addFilterQuery("item_price:[540000 TO 2279900]");
        solrQuery.set("df","item_sell_point");
        //4. 执行查询
        QueryResponse response = solrServer.query(solrQuery);
        //5. 获取结果集
        SolrDocumentList results = response.getResults();
        //6. 遍历结果集 打印
        System.out.println("查询的总记录数:"+results.getNumFound());
        for (SolrDocument solrDocument : results) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
        }

    }
}
