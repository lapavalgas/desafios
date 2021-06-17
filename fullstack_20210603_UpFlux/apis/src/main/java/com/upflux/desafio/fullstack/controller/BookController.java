package com.upflux.desafio.fullstack.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upflux.desafio.fullstack.model.Book;
import com.upflux.desafio.fullstack.model.dto.RequestDTO;
import com.upflux.desafio.fullstack.model.dto.ResponseDTO;
import com.upflux.desafio.fullstack.service.BookService;
import com.upflux.desafio.fullstack.utils.Utils;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping
	public ResponseEntity<ResponseDTO> create(@RequestBody RequestDTO request) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setBook(bookService.save(request.builderNewBook()));
			_response.setStatuscode(201);
			_response.setMenssage("Livro cadastrado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.CREATED);
		} catch (Exception e) {
			Utils.logError(e);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setStatuscode(500);
			_response.setMenssage("Falha ao cadastrar livro, tente novamente!");
			return new ResponseEntity<>(_response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<ResponseDTO> readAll() throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setBooks(bookService.readAll());
			_response.ThrowExceptionIsEmptyBooks();
			_response.setStatuscode(200);
			_response.setMenssage("Os Livros foram localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/byStock")
	public ResponseEntity<ResponseDTO> readAllOrderByQnttStock() throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setBooks(bookService.readAllOrderByQnttStock());
			_response.ThrowExceptionIsEmptyBooks();
			_response.setStatuscode(200);
			_response.setMenssage("Os Livros foram localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> readId(@PathVariable String id) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setBook(bookService.readById(id));
			_response.setStatuscode(200);
			_response.setMenssage("O Livro foi localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}/avaiable")
	public ResponseEntity<ResponseDTO> readAvaialbeId(@PathVariable String id) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setBook(bookService.isAvailableBook(id));
			_response.setStatuscode(200);
			_response.setMenssage("O Livro foi localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<ResponseDTO> readTitle(@PathVariable String title) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response = readOneOrManyBookByTitle(_response, title);
			_response.ThrowExceptionIsEmptyBooks();
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	public ResponseDTO readOneOrManyBookByTitle(ResponseDTO _response, String title) throws Exception {
		try {
			_response.setBook(bookService.readByTitle(title));
			if (_response.getBook() == null) {
				throw new Exception("Não foi localizado Livro");
			}
			_response.setStatuscode(200);
			_response.setMenssage(String.format("O Livro com o título '%s' foi localizado com sucesso!", title));
		} catch (Exception e) {
			_response.setBooks(bookService.readTitleContaining(title));
			if (_response.getBooks() == null) {
				throw new Exception("Não foi localizado Livro");
			}
			_response.ThrowExceptionIsEmptyBooks();
			_response.setStatuscode(200);
			_response.setMenssage(String.format("Os Livros com o título '%s' foram localizados com sucesso!", title));
		}
		return _response;
	}

	@GetMapping("/autor/{autor}")
	public ResponseEntity<ResponseDTO> readAutor(@PathVariable String autor) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setBooks(bookService.readAutorContaining(autor));
			_response.ThrowExceptionIsEmptyBooks();
			_response.setStatuscode(200);
			_response.setMenssage(String.format("Os Livros localizados para o autor '%s', foram:", autor));
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateId(@RequestBody RequestDTO request, @PathVariable String id)
			throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			Book _book = bookService.readBookById(id);
			_book.setTitle(request.getBook().getTitle());
			_book.setAutor(request.getBook().getAutor());
			_response.setBook(bookService.save(_book));
			_response.setStatuscode(200);
			_response.setMenssage("Livro Atualizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setStatuscode(404);
			_response.setMenssage("Falha ao atualizar livro, não localizado!");
			return new ResponseEntity<>(_response, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteId(@PathVariable String id) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			bookService.readBookById(id);
			bookService.delete(id);
			_response.setStatuscode(200);
			_response.setMenssage("Livro deletado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setStatuscode(404);
			_response.setMenssage("Falha ao deletar o livro, não localizado!");
			return new ResponseEntity<>(_response, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/!/delete/all")
	public ResponseEntity<ResponseDTO> deleteAll() throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			bookService.deleteAll();
			_response.setStatuscode(200);
			_response.setMenssage("Dados de livros deletados com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setStatuscode(500);
			_response.setMenssage("Falha ao deletar os dados de livros, tente novamente!");
			return new ResponseEntity<>(_response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
