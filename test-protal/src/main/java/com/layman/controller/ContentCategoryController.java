package com.layman.controller;

import java.util.List;

import com.layman.pojo.EasyUIDataGridResult;
import com.layman.pojo.TaotaoResult;
import com.layman.pojo.TbContent;
import com.layman.service.ContentCategoryService;
import com.layman.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容分类的处理controller
 * @title ContentCategoryController.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 * @author ljh
 * @version 1.0
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService service;
	/**
	 * url : '/content/category/list',
		animate: true,
		method : "GET",
		参数: id
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		//1.引入服务
		//2.注入服务
		//3调用
		List<EasyUITreeNode> list = service.getContentCategoryList(parentId);
		return list;
	}

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId,String name){
	    return service.createContextCategory(parentId,name);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public TaotaoResult updateContextCategory(@RequestParam("id") Long id,@RequestParam("name") String name){
        System.out.println("update is ok");
        System.out.println("id = "+id +"name:"+ name);
	    service.updateContextCategory(id,name);
	    return TaotaoResult.ok();
    }

}
