package com.onpassive.admin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

@Entity
public class Book {

	@Id
	
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
//	@SequenceGenerator(sequenceName = "book_seq", allocationSize = 1, name = "Book_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min=3, max=10)
	private String title;
	@NotNull
	@Size(min=4,message="auther name should be atleast 4 charecters")
	private String author;
	private String category;
	private String publisher;
	private float listPrice;
	private float ourPrice;

	@Column(columnDefinition = "number(1,0)")
	private boolean active = true;
//	private char active='1';

	private String publicationDate;
	private String language;
	private int numberOfPages;
	private String format;
	private int isbn;
	private float shippingWeight;

	@Column(columnDefinition = "clob")
	private String description;
	private int inStockNumber;

	@Transient
	private MultipartFile bookImage;

//		@OneToMany(mappedBy = "book")
//		@JsonIgnore
//		private List<BookToCartItem> bookToCartItemList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public float getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(float shippingWeight) {
		this.shippingWeight = shippingWeight;
	}

	public float getListPrice() {
		return listPrice;
	}

	public void setListPrice(float listPrice) {
		this.listPrice = listPrice;
	}

	public float getOurPrice() {
		return ourPrice;
	}

	public void setOurPrice(float ourPrice) {
		this.ourPrice = ourPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

	public MultipartFile getBookImage() {
		return bookImage;
	}

	public void setBookImage(MultipartFile bookImage) {
		this.bookImage = bookImage;
	}

//		public List<BookToCartItem> getBookToCartItemList() {
//			return bookToCartItemList;
//		}
//
//		public void setBookToCartItemList(List<BookToCartItem> bookToCartItemList) {
//			this.bookToCartItemList = bookToCartItemList;
//		}

}
