package com.layman.service;

import com.layman.pojo.EasyUIDataGridResult;
import com.layman.pojo.EasyUITreeNode;
import com.layman.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    // 通过节点的Id查询该节点的子节点
    List<EasyUITreeNode> getContentCategoryList(Long parentId);

    /**
     * @param parentId  * 父节点的ＩＤ
     * @param name     * 子节点的名称　
     * @return
     */
    //添加内容分类
    TaotaoResult createContextCategory(Long parentId,String name);

    //修改内容
    void updateContextCategory(Long nodeId,String name);

    EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer row);
}
