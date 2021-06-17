package com.upflux.desafio.fullstack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upflux.desafio.fullstack.model.Book;
import com.upflux.desafio.fullstack.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public Book readById(String id) {
		return bookRepository.findById(id).get();
	}

	public Book readByTitle(String title) {
		return bookRepository.findByTitleIgnoreCase(title);
	}

	public List<Book> readTitleContaining(String title) {
		return bookRepository.findByTitleContainingIgnoreCase(title);
	}

	public List<Book> readAutorContaining(String autor) {
		return bookRepository.findByAutorContainingIgnoreCase(autor);
	}

	public List<Book> readAll() {
		return bookRepository.findAll();
	}
	
	public List<Book> readAllOrderByQnttStock() {
		return bookRepository.findAllByOrderByBookLoansAsc();
	}

	public void delete(String id) {
		bookRepository.deleteById(id);
	}

	public void deleteAll() {
		bookRepository.deleteAll();
	}

	public Book readBookById(String id) throws Exception {
		Book _book = readById(id);
		if (_book.getId().isEmpty()) {
			throw new Exception("Não foi localizado o livro!");
		} else {
			return _book;
		}
	}

	public Book decreaseAvailable(Book _book, String idLoan) throws Exception {
		_book.setQnttStock(_book.getQnttStock() - 1);
		if (_book.getBookLoans() == null) {
			_book.setBookLoans(new ArrayList<>());
		}
		_book.getBookLoans().add(idLoan);
		_book = save(_book);
		return _book;
	}

	public Book increaseAvailable(Book _book) throws Exception {
		_book.setQnttStock(_book.getQnttStock() + 1);
		_book = save(_book);
		return _book;
	}

	public Book isAvailableBook(String id) throws Exception {
		Book _book = readBookById(id);
		if (_book.getQnttStock() >= 1) {
			return _book;
		} else {
			throw new Exception("O livro não está disponível!");
		}
	}
}
