package com.layman.search.controller;

import com.layman.pojo.SearchResult;
import com.layman.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {


    @Autowired
    private SearchService searchService;

    /**
     * 根据条件搜索商品数据
     *
     * @param page
     * @param queryString
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "1") Integer page, @RequestParam(value = "q") String queryString, Model model) throws Exception {
//        System.out.println(queryString);
//        System.out.println(page);
        int i = 10/0;
        SearchResult result = searchService.search(queryString, page, 60);
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", result.getPageCount()); //总页数
        model.addAttribute("itemList", result.getItemList());
        model.addAttribute("page", page);
        return "/search";
    }

}
