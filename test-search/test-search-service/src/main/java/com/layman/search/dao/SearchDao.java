package com.layman.search.dao;

import com.layman.pojo.SearchItem;
import com.layman.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 从索引库中搜索商品的dao
 */
@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    /**
     * 根据查询的条件查询结果集
     *
     * @param query
     * @return
     * @throws Exception
     */
    public SearchResult search(SolrQuery query) throws Exception {

        SearchResult searchResult = new SearchResult();
        //1. 创建solrserver对象 由spring管理注入
        //2. 直接执行查询
        QueryResponse response = solrServer.query(query);
        //3. 获取结果集
        SolrDocumentList results = response.getResults();
        //设置SearchResult的总记录数
        searchResult.setRecordCount(results.getNumFound());
        //4. 遍历结果集
        //定义一个集合
        List<SearchItem> itemList = new ArrayList<>();

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        for (SolrDocument solrDocument : results) {
            // 将solrdocment中的属性一个个设置到SearchItem中
            SearchItem searchItem = new SearchItem();
            searchItem.setCategory_name(solrDocument.get("item_category_name").toString());
            searchItem.setId(Long.parseLong(solrDocument.get("id").toString()));
            //searchItem.setImages(solrDocument.get("item_image").toString());
            searchItem.setImages("http://118.24.27.161:8081/group1/M00/00/00/rBsADlwKDNOASoxXAACuh05ZlZg129.jpg");
            searchItem.setPrice((Long) solrDocument.get("item_price"));
            searchItem.setSell_point(solrDocument.get("item_sell_point").toString());
            //取高亮
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            //判断list是否为空
            String highlightStr = "";
            if (list != null && list.size() > 0) {
                //有高亮
                highlightStr = list.get(0);
            } else {
                highlightStr = solrDocument.get("item_sell_point").toString();
            }
            searchItem.setTitle(highlightStr);
            //searchItem  封装到SearchResult中的itemList属性中
            itemList.add(searchItem);
        }
        //5. 设置SearchResult的属性
        searchResult.setItemList(itemList);

        return searchResult;
    }
}
