package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.BookDAO;
import com.sist.web.entity.Book;

@RestController
@CrossOrigin(origins = "*")
public class BookRestController {
	@Autowired
	private BookDAO dao;
	
	@GetMapping("/book/list_react")
	public Map book_list(int page,String title)
	{
		if(title==null || title=="")
			title="";
		
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Book> list=dao.bookListData(start,title);
		int totalpage=dao.bookTotalPage(title);
		for(Book vo:list)
		{
			String price=vo.getPrice();
			String formattedNumber = String.format("%,d", Long.parseLong(price))+"원";
			vo.setPrice(formattedNumber);
		}
		Map map=new HashMap();
		map.put("list", list);
		map.put("totalpage", totalpage);
		return map;
	}
	
	@GetMapping("/book/detail_react")
	public Book book_detail(int no)
	{
		Book vo=dao.findByNo(no);
		String price=vo.getPrice();
		String formattedNumber = String.format("%,d", Long.parseLong(price))+"원";
		vo.setPrice(formattedNumber);
		return vo;
	}
}
