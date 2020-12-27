package com.onpassive.admin.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.onpassive.admin.domain.Book;
import com.onpassive.admin.mail.Email;

public interface BookService {
	
	Book save(Book book);
	
	List<Book> findAll();
	
	Book finedOne(Long id);
	
	void removeOne(Long id);
	public ByteArrayInputStream load();
	
	List<Book> findPdf();
	
	public void sendMail(final Email email);

}
