package com.layman.controller;

import com.layman.pojo.EasyUIDataGridResult;
import com.layman.pojo.EasyUITreeNode;
import com.layman.pojo.TaotaoResult;
import com.layman.pojo.TbItem;
import com.layman.service.ItemCatService;
import com.layman.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){

        //1. 引入服务
        //2. 注入服务
        //3. 调用服务
        return itemService.getItenList(page,rows);

    }

    @RequestMapping("/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(name = "id",defaultValue = "0") Long parentId){
        return  itemCatService.getItemCatList(parentId);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult saveItem(TbItem item, String desc){
//	//1.引入服务
        //2.注入服务
        //3.调用
        return itemService.saveItem(item, desc);
    }
}
