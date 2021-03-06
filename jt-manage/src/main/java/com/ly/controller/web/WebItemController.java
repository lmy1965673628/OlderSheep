package com.ly.controller.web;

import com.ly.pojo.Item;
import com.ly.pojo.ItemDesc;
import com.ly.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//要求返回的是json串
@RestController
@RequestMapping("/web/item")
public class WebItemController {
	
	@Autowired
	private ItemService itemService;
	/**
	 * "http://manage.jt.com/web/item/findItemById?itemId=562379";
	 */
	@RequestMapping("findItemById")
	public Item findItemById(Long itemId) {
		
		return itemService.findItemById(itemId);
	}
	
	//http://manage.jt.com/web/item/findItemDescById
	@RequestMapping("findItemDescById")
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemService.findItemDescById(itemId);
	}
}
