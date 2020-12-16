package com.onpassive.admin.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onpassive.admin.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
