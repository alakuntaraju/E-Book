package com.onpassive.admin.service;

import java.util.List;

import com.onpassive.admin.domain.Book;

public interface BookService {
	
	Book save(Book book);
	
	List<Book> findAll();
	
	Book finedOne(Long id);
	
	void removeOne(Long id);

}
