package com.onpassive.admin.serviceimpl;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.onpassive.admin.domain.Book;
import com.onpassive.admin.helper.ExcelHelper;
import com.onpassive.admin.repository.BookRepository;
import com.onpassive.admin.service.BookService;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookrepository;

	@Override
	public Book save(Book book) {
		return bookrepository.save(book);
	}

	@Override
	public List<Book> findAll() {
		return(List<Book>) bookrepository.findAll();
	}

	@Override
	public Book finedOne(Long id) {
		return bookrepository.findById(id).get();
	}

	@Override
	public void removeOne(Long id) {
		bookrepository.deleteById(id);
		
	}
	@Override
	public ByteArrayInputStream load() {
	    List<Book> books = bookrepository.findAll();

	    ByteArrayInputStream in = ExcelHelper.booksToExcel(books);
	    return in;
	  }
	public List<Book> listAll() {
        return bookrepository.findAll(Sort.by("email").ascending());
    }
     

}
