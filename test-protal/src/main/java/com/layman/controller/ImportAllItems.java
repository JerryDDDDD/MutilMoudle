package com.layman.controller;

import com.layman.pojo.TaotaoResult;
import com.layman.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class ImportAllItems  {

    @Autowired
    private SearchService searchService;

    /**
     * 导入所有的商品数据到索引库中
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/importAll",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult importAll() throws Exception {

        //1. 引入服务 注入服务

        //2. 调用
        searchService.importAllSearchItems();
        return TaotaoResult.ok();
    }
}
