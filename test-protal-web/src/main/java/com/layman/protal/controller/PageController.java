package com.layman.protal.controller;

import com.layman.pojo.TbContent;
import com.layman.protal.pojo.Ad1Node;
import com.layman.service.ContentService;
import com.layman.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示首页
 */

@Controller
public class PageController {

    @Autowired
    private ContentService contentService;

    @Value("${AD1_CATEGORY_ID}")
    private Long categoryId;

    @Value("${AD1_HEIGHT}")
    private String HEIGHT;

    @Value("${AD1_HEIGHT_B}")
    private String HEIGHT2;

    @Value("${AD1_WIDTH}")
    private String WIDTH;

    @Value("${AD1_WIDTH_B}")
    private String WIDTH2;

    @RequestMapping("/index")
    public String showIndex(Model model){
        // 引入服务
        // 注入服务
        // 添加业务逻辑，根据内容分类的id查询内容列表
        List<TbContent> contentList = contentService.getContentListByCatId(categoryId);
        // 转成自定义的POJO  AD1BODE的列表
        List<Ad1Node> nodes = new ArrayList<>();
        for (TbContent tbContent:contentList){
            Ad1Node node = new Ad1Node();
            node.setAlt(tbContent.getSubTitle());
            node.setHeight(HEIGHT);
            node.setHeightB(HEIGHT2);
            node.setHref(tbContent.getUrl());
            node.setSrc(tbContent.getPic());
            node.setSrcB("http://img2.3lian.com/img2007/10/24/011.jpg");
            node.setWidth(WIDTH);
            node.setWidthB(WIDTH2);
            nodes.add(node);
        }
        // 传递给JSP
        model.addAttribute("ad1",JsonUtils.objectToJson(nodes));
        return "/index";
    }

}
