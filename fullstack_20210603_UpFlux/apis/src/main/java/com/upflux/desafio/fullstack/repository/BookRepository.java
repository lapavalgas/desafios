package com.upflux.desafio.fullstack.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.upflux.desafio.fullstack.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {

	List<Book> findAllByOrderByBookLoansAsc();
	
	Book findByTitleIgnoreCase(String title);

	List<Book> findByTitleContainingIgnoreCase(String title);

	List<Book> findByAutorContainingIgnoreCase(String autor);

}