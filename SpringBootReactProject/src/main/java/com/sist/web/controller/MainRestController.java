package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
@RestController
@CrossOrigin(origins="*")
public class MainRestController {
	
	@Autowired
	private BoardDAO bDao;
	
	@Autowired
	private BookDAO bkDao;

	
	@GetMapping("/board/main_react")
	public List<Board> main_board(){
		return bDao.boardMainData();
	}
	@GetMapping("/book/main_react")
	public List<Book> main_book(){
		return bkDao.bookMainList();
	}
}
