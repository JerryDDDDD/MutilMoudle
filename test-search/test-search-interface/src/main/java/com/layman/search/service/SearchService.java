package com.layman.search.service;

import com.layman.pojo.SearchResult;
import com.layman.pojo.TaotaoResult;

import java.io.IOException;

public interface SearchService {

    // 导入所有的商品数据到索引中
    TaotaoResult importAllSearchItems() throws Exception;

    // 根据搜索的条件查询搜索的结果

    /**
     *
     * @param queryString  查询的主条件
     * @param page          页码
     * @param rows          行数
     * @return
     * @throws Exception
     */
    SearchResult search(String queryString,Integer page,Integer rows) throws Exception;
}
