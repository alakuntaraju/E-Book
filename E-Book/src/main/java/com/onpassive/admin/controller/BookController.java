package com.onpassive.admin.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.admin.domain.Book;
//import com.onpassive.admin.mail.Email;
import com.onpassive.admin.pdf.GeneratePdf;
import com.onpassive.admin.service.BookService;

@Controller
//@RestController
@RequestMapping("Book")
public class BookController<EmailService> {
	@Autowired
	private BookService bookservice;
	
//	private EmailService emailService;

	@GetMapping("/add")
	public String addbook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";

	}

	@PostMapping("/add")
		public String addBookPost(@ModelAttribute("book") Book book) {
		bookservice.save(book);
//		public Book addBookPost( Book book) {
//		Book saveBook=bookservice.save(book);
		MultipartFile bookImage = book.getBookImage();
		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:BookList";
//		return saveBook;

	}

	@GetMapping("/BookList")
	public String bookList(Model model) {
//	public List<Book> bookList() {
		List<Book> books = bookservice.findAll();
		model.addAttribute("books", books);
		return "BookList";
//		return books;
	}

	@GetMapping("/bookInfo")
		public String bookinfo(@RequestParam Long id, Model model) {
//	public Book bookinfo(@RequestParam Long id) {
		Book book = bookservice.finedOne(id);
		model.addAttribute("book", book);
		return "bookInfo";
//		return book;

	}

	@GetMapping("/update")
	public String updateBookGet(@RequestParam Long id, Model model) {
		Book book = bookservice.finedOne(id);
		model.addAttribute("book", book);
		return "updateBook";

	}

	@PostMapping("/update")
	public String updateBookPost(@ModelAttribute("book") Book book) {
//		public String updateBookPost(@RequestBody Book book) {
		bookservice.save(book);
		MultipartFile bookImage = book.getBookImage();

		if (!bookImage.isEmpty()) {
			try {
				byte[] bytes = bookImage.getBytes();
				String name = book.getId() + ".png";
				Files.delete(Paths.get("src/main/resources/static/image/book/" + name));
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "redirect:BookList";

	}

	@GetMapping("/delete")
	public String deleteBook(@RequestParam Long id) {
		bookservice.removeOne(id);
		return "redirect:BookList";
//		return "successfully deleted";

	}

	@GetMapping("/getData")
	public Book deleteBook() {
		Book book = new Book();
		return book;

	}
	@GetMapping("/download")
	  public ResponseEntity<Resource> getFile() {
	    String filename = "books.xlsx";
	    InputStreamResource file = new InputStreamResource(bookservice.load());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	        .body(file);
	  }
	
    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

        List books = (List<Book>) bookservice.findAll();

        ByteArrayInputStream bis= GeneratePdf.citiesReport(books);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
//    public String service() {
//    	Email email = new Email();
//        email.setFrom("from-mail1@gmail.com");
//        email.setTo("to-mail2@mgmail.com");
//        email.setSubject("This is a test mail");
//        email.setMessageText("This is a sample text message.");
//        return emailService.sendMailWithAttachment(email,
//           "./samplepic.jpg");
     
//		return emailService ;
    	
//    }
}
