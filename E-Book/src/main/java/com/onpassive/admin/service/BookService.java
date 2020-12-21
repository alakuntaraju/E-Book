package com.onpassive.admin.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.onpassive.admin.domain.Book;

public interface BookService {
	
	Book save(Book book);
	
	List<Book> findAll();
	
	Book finedOne(Long id);
	
	void removeOne(Long id);
	public ByteArrayInputStream load();
}
