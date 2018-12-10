package com.layman.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.layman.mapper.TbItemDescMapper;
import com.layman.mapper.TbItemMapper;
import com.layman.pojo.*;
import com.layman.service.ItemService;
import com.layman.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper descmapper;

    @Override
    public EasyUIDataGridResult getItenList(Integer page, Integer rows) {
        // 1. 设置分页的信息使用pageHelper
        if (page == null) page = 1;
        if (rows == null) rows = 30;
        PageHelper.startPage(page,rows);
        // 2. 注入mapper
        // 3. 创建example 对象不需要设置查询条件
        TbItemExample example = new TbItemExample();
        // 4. 根据mapper调用所查询的方法
        List<TbItem> list = tbItemMapper.selectByExample(example);
        // 5. 获取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        // 6. 封装到EasyUIDataGrideResult
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        // 7. 返回
        return result;
    }

    @Override
    public TaotaoResult saveItem(TbItem item, String desc) {
        //生成商品的id
        long itemId = IDUtils.genItemId();
        //1.补全item 的其他属性
        item.setId(itemId);
        item.setCreated(new Date());
        //1-正常，2-下架，3-删除',
        item.setStatus((byte) 1);
        item.setUpdated(item.getCreated());
        //2.插入到item表 商品的基本信息表
        tbItemMapper.insertSelective(item);
        //3.补全商品描述中的属性
        TbItemDesc desc2 = new TbItemDesc();
        desc2.setItemDesc(desc);
        desc2.setItemId(itemId);
        desc2.setCreated(item.getCreated());
        desc2.setUpdated(item.getCreated());
        //4.插入商品描述数据
        //注入tbitemdesc的mapper
        descmapper.insertSelective(desc2);
        //5.返回taotaoresult
        return TaotaoResult.ok();
    }
}
