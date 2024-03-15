package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
@RestController
@CrossOrigin(origins = "*")
public class BoardRestController {
  @Autowired
  private BoardDAO dao;
  
  @GetMapping("/board/list_react")
  public List<Board> boardListData(int page)
  {
	  Map map=new HashMap();
	  int rowSize=10;
	  int start=(rowSize*page)-rowSize;
	  List<Board> list=dao.boardListData(start);
	  for(Board vo:list)
		{
			String date=vo.getRegdate().substring(0,vo.getRegdate().indexOf(" "));
			vo.setRegdate(date);
		}
	  int count=(int)dao.count();
	  int totalpage=(int)(Math.ceil(count/10.0));
	  map.put("curpage", page);
	  map.put("totalpage", totalpage);
	  map.put("list", list);
	  return list;
  }
  @GetMapping("/board/total_react")
  public String boardtotalpage()
  {
	  int count=(int)dao.count();
	  String totalpage=String.valueOf((int)Math.ceil(count/10.0));
	  System.out.println(totalpage);
	  return totalpage;
	 
  }
  @PostMapping("/board/insert_react")
  public String boardInsert(Board vo)
  {
	  String result="";
	  try
	  {
		  dao.save(vo);
		  result="yes";  
	  }catch(Exception ex)
	  {
		  result="no";
	  }
	  return result;
  }
  @GetMapping("/board/detail_react")
  public Board boardDetail(int no)
  {
	  Board vo=dao.findByNo(no);
	  vo.setHit(vo.getHit()+1);
	  dao.save(vo);
	  String date=vo.getRegdate().substring(0,vo.getRegdate().indexOf(" "));
	  vo.setRegdate(date);
	  vo=dao.findByNo(no);
	  return vo;
  }
  @GetMapping("/board/update_react")
  public Board boardUpdate(int no)
  {
	  Board vo=dao.findByNo(no);
	  return vo;
  }
  @PostMapping("/board/update_ok_react")
  public String boardUpdateOk(Board vo)
  {
	  Board dbVO=dao.findByNo(vo.getNo());
	  String result="";
	  if(vo.getPwd().equals(dbVO.getPwd()))
	  {
		  result="yes";
		  vo.setHit(dbVO.getHit());
		  dao.save(vo);
	  }
	  else
	  {
		  result="no";
	  }
	  
	  return result;  
  }
  @PostMapping("/board/delete_react")
  public String boardDelete(int no,String pwd)
  {
	  String result="";
	  Board vo=dao.findByNo(no);
	  if(vo.getPwd().equals(pwd))
	  {
		 result="yes";
		 dao.delete(vo);
	  }
	  else
	  {
		  result="no";
	  }
	  return result;
  }
}