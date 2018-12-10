package com.layman.service;

import com.layman.pojo.TaotaoResult;
import com.layman.pojo.TbContent;

import java.util.List;

public interface ContentService {

    /**
     * 增加内容
     * @param content
     * @return
     */
    TaotaoResult saveContent(TbContent content);

    public List<TbContent> getContentListByCatId(Long categoryId);
}
