package com.layman.controller;

import com.layman.pojo.EasyUIDataGridResult;
import com.layman.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){

        //1. 引入服务
        //2. 注入服务
        //3. 调用服务
        return itemService.getItenList(page,rows);

    }
}
