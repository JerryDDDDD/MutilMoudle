package com.layman.service;

import com.layman.pojo.EasyUIDataGridResult;
import com.layman.pojo.TaotaoResult;
import com.layman.pojo.TbItem;

/**
 * 商品相关的Service接口
 */
public interface ItemService {

    /**
     * 根据单前页码和行数进行分页查询
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGridResult getItenList(Integer page,Integer rows);

    TaotaoResult saveItem(TbItem item, String desc);
}
