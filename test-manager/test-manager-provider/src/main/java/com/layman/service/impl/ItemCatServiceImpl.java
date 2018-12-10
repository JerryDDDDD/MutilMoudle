package com.layman.service.impl;

import com.layman.mapper.TbItemCatMapper;
import com.layman.pojo.EasyUITreeNode;
import com.layman.pojo.TbItemCat;
import com.layman.pojo.TbItemCatExample;
import com.layman.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //1.根据父节点查询子节点
        TbItemCatExample example = new TbItemCatExample();
        //2.设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //3.设置parentId
        criteria.andParentIdEqualTo(parentId);
        //4.执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //5.将TbItemCat集合转化成EasyUITreeNode集合
        List<EasyUITreeNode> easyUITreeNodeList = new ArrayList<>();
        for (TbItemCat itemCat:list) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(itemCat.getId());
            easyUITreeNode.setText(itemCat.getName());
            easyUITreeNode.setState(itemCat.getIsParent()?"closed":"open");
            easyUITreeNodeList.add(easyUITreeNode);
        }
        return easyUITreeNodeList;
    }
}
