package com.jsp.libraryManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.libraryManagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	 Book findByIsbn(String isbn);
	}
