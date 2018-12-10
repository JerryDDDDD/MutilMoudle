package com.layman.search.mapper;

import com.layman.pojo.SearchItem;

import java.util.List;

/**
 * 定义mapper 关联查询3张表 查询出搜索时的商品数据
 */
public interface SearchItemMapper {

    //查询所有商品的数据
    List<SearchItem> getSearchItemList();
}
