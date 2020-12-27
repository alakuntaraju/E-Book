package com.onpassive.admin.serviceimpl;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.onpassive.admin.domain.Book;
import com.onpassive.admin.helper.ExcelHelper;
import com.onpassive.admin.mail.Email;
import com.onpassive.admin.repository.BookRepository;
import com.onpassive.admin.service.BookService;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.io.File;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookrepository;
	
	private JavaMailSender javaMailSender;


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

	@Override
	public List<Book> findPdf() {
		// TODO Auto-generated method stub
	
		return (List<Book>) bookrepository.findAll();
	}

	@Override
	public void sendMail(final Email email) {
		// TODO Auto-generated method stub
		
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
	      simpleMailMessage.setSubject(email.getSubject());
	      simpleMailMessage.setFrom(email.getFrom());
	      simpleMailMessage.setTo(email.getTo());
	      simpleMailMessage.setText(email.getMessageText());
	      javaMailSender.send(simpleMailMessage);
	   }
	   public void sendMailWithAttachment(final Email email,
	         String filePath) throws Exception{
	      MimeMessage mimeMessage=javaMailSender.createMimeMessage();
	      MimeMessageHelper mimeMessageHelper=new
	         MimeMessageHelper(mimeMessage,true);
	      mimeMessageHelper.setSubject(email.getSubject());
	      mimeMessageHelper.setFrom(email.getFrom());
	      mimeMessageHelper.setTo(email.getTo());
	      mimeMessageHelper.setText(email.getMessageText());
	      FileSystemResource file=new FileSystemResource(new
	         File(filePath));
	      mimeMessageHelper.addAttachment("Sample File",file);
	      javaMailSender.send(mimeMessage);
	   }
		
	}
     
