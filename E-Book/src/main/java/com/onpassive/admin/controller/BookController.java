package com.onpassive.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.admin.domain.Book;
import com.onpassive.admin.service.BookService;

@Controller
@RequestMapping("Book")
public class BookController {
	@Autowired
	private BookService bookservice;

	@GetMapping("/add")
	public String addbook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";

	}

	@PostMapping("/add")
	public String addBookPost(@ModelAttribute("book") Book book) {
		bookservice.save(book);
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

	}

	@GetMapping("/BookList")
	public String bookList(Model model) {
		List<Book> books = bookservice.findAll();
		model.addAttribute("books", books);
		return "BookList";
	}

	@GetMapping("/bookInfo")
	public String bookinfo(@RequestParam Long id, Model model) {
		Book book = bookservice.finedOne(id);
		model.addAttribute("book", book);
		return "bookInfo";

	}

	@GetMapping("/update")
	public String updateBookGet(@RequestParam Long id, Model model) {
		Book book = bookservice.finedOne(id);
		model.addAttribute("book", book);
		return "updateBook";

	}

	@PostMapping("/update")
	public String updateBookPost(@ModelAttribute("book") Book book) {
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

	}

	@GetMapping("/getData")
	public Book deleteBook() {
		Book book = new Book();
		return book;

	}
}
