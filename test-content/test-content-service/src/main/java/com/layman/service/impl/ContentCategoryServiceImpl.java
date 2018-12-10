package com.layman.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.layman.mapper.TbContentCategoryMapper;
import com.layman.mapper.TbContentMapper;
import com.layman.pojo.*;
import com.layman.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        //1. 注入mapper
        //2. 创建example
        TbContentCategoryExample example = new TbContentCategoryExample();
        //3. 设置条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //4. 执行查询
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        //5. 转成EasyUITreeNode 列表
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory: list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            node.setText(tbContentCategory.getName()); //分类名称
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public TaotaoResult createContextCategory(Long parentId, String name) {
        //1. 构建一个对象,补全其它的属性
        //2. 插入contentCategory表数据
        //3. 返回taotaoResult包涵内容分类的id（主键返回）
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setCreated(new Date());
        contentCategory.setIsParent(false); //新增的节点都是叶子节点
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setStatus(1);
        contentCategory.setUpdated(contentCategory.getCreated());
        // 判断如果要添加节点的父节点 如果本身是叶子节点 则更新其为父节点
        //1. 获取父节点对象
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (tbContentCategory.getIsParent()==false){
            tbContentCategory.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
        }
        tbContentCategoryMapper.insertSelective(contentCategory);

        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public void updateContextCategory(Long nodeId, String name) {
        if (nodeId == null) {
            System.out.println("nodeId is null");
        }
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(nodeId);
        if (tbContentCategory == null) {
            System.out.println("taContextCategory is null");
        } else {
            tbContentCategory.setName(name);
            tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
        }
    }

    @Override
    public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer row) {
        //1. 设置分页信息使用的PageHelper
        PageHelper.startPage(page,row);
        //2. 创建example对象
        TbContentExample example = new TbContentExample();
        //3. 设置查询条件
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        //4. 调用mapper查询
        List<TbContent> list = tbContentMapper.selectByExample(example);
        //5. 获取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        //6. 封装到EasyUIDataGrideResult
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        return result;
    }
}
