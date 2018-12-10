package com.layman.search.service.impl;

import com.layman.pojo.SearchItem;
import com.layman.pojo.SearchResult;
import com.layman.pojo.TaotaoResult;
import com.layman.search.dao.SearchDao;
import com.layman.search.mapper.SearchItemMapper;
import com.layman.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private HttpSolrServer solrServer;

    @Autowired
    private SearchDao searchDao;

    @Override
    public TaotaoResult importAllSearchItems() throws Exception {

        //1. 注入mapper
        //2. 调用mapper的方法
        List<SearchItem> searchItemList = searchItemMapper.getSearchItemList();
        //3. 通过solrj 将这些数据写入到索引库中
        //3.1 创建httpsolrservice
        //3.2 创建solrinputdocument 将列表中的元素一个个放到索引库中
        for (SearchItem searchItem : searchItemList) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", searchItem.getId().toString()); //这里是字符串需要转化为String
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSell_point());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImages());
            document.addField("item_category_name", searchItem.getCategory_name());
            document.addField("item_desc", searchItem.getItem_desc());
            //添加到索引库
            solrServer.add(document);
            //solrServer.deleteById(searchItem.getId().toString());
        }
        solrServer.commit();
        return TaotaoResult.ok();
    }

    @Override
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {

        //1. 创建solrQuery对象
        SolrQuery query = new SolrQuery();

        //2. 设置主查询条件
        if (!queryString.isEmpty()) {
            query.setQuery(queryString);
        } else {
            query.setQuery("*:*");
        }
        //2.1 设置过滤条件 设置分页
        if (page == null)
            page = 1;
        if (rows == null)
            rows = 60;
        query.setStart((page - 1) * rows); //page-1*rows
        query.setRows(rows);
        //2.2 设置默认的搜索域
        query.set("df", "item_keywords");
        //2.3 设置高亮
        query.setHighlight(true);
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        query.addHighlightField("item_title"); // 设置高亮显示的域
        //3. 调用dao的方法 返回的是SearchResult 只包含了总记录数和商品的列表

        SearchResult search = searchDao.search(query);

        //4. 设置SearchResult的总页数
        Long pageCount = 0l;
        pageCount = search.getRecordCount() / rows;
        if (search.getRecordCount() % rows > 0) {
            pageCount++;
        }
        search.setPageCount(pageCount);
        //5. 返回
        return search;
    }
}
