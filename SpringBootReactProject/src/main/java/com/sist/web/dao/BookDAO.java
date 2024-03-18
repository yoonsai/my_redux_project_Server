package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Book;

public interface BookDAO extends JpaRepository<Book, Integer>{
	@Query(value="SELECT * FROM books WHERE title LIKE CONCAT('%',:title,'%') ORDER BY no ASC "
			+ "LIMIT :start,12",nativeQuery=true)
	public List<Book> bookListData(@Param("start") Integer start,@Param("title") String title);
	
	@Query(value="SELECT COUNT(*) FROM books WHERE title LIKE CONCAT('%',:title,'%')")
	public int bookTotalPage(@Param("title") String title);
	
	public Book findByNo(int no);
	
	@Query(value="SELECT * FROM books ORDER BY jjim DESC "
			+ "LIMIT 0,4",nativeQuery=true)
	public List<Book> bookMainList();
}
