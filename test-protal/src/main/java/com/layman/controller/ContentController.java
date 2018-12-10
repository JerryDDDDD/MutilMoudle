package com.layman.controller;

import com.layman.pojo.EasyUIDataGridResult;
import com.layman.pojo.TaotaoResult;
import com.layman.pojo.TbContent;
import com.layman.service.ContentCategoryService;
import com.layman.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentCategoryService service;

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContent(Long categoryId,
                                           @RequestParam(value = "page",defaultValue = "1") Integer page ,
                                           @RequestParam(value = "rows",defaultValue = "20") Integer rows){
        return service.getContentList(categoryId,page,rows);
    }

    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public TaotaoResult saveContent(TbContent tbContent){
        return contentService.saveContent(tbContent);
    }

}
