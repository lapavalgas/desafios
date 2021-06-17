package com.upflux.desafio.fullstack.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

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
import com.upflux.desafio.fullstack.model.Loan;
import com.upflux.desafio.fullstack.model.dto.RequestDTO;
import com.upflux.desafio.fullstack.model.dto.ResponseDTO;
import com.upflux.desafio.fullstack.service.BookService;
import com.upflux.desafio.fullstack.service.LoanService;
import com.upflux.desafio.fullstack.utils.Utils;

@RestController
@RequestMapping("/api/book/{id}/loan")
public class LoanController {

	@Autowired
	LoanService loanService;

	@Autowired
	BookService bookService;

	@PostMapping()
	public ResponseEntity<ResponseDTO> create(@PathVariable String id, @RequestBody RequestDTO request)
			throws Exception {
		try {
			Book _book = bookService.isAvailableBook(id);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setLoan(request.getLoan());
			_response.getLoan().setBookId(id);
			_response.getLoan().setBorrowed(Utils.getCurrentTimeAsDate());
			_response.setLoan(loanService.save(request.builderNewLoan()));
			_book = bookService.decreaseAvailable(_book, _response.getLoan().getId());
			_response.setBook(_book);
			_response.setStatuscode(201);
			_response.setMenssage("Empréstimo efetuado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.CREATED);
		} catch (Exception e) {
			Utils.logError(e);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			if (e.getMessage().contains("não está disponível")) {
				_response.setStatuscode(400);
				_response.setMenssage("O livro não está disponível!");
				return new ResponseEntity<>(_response, HttpStatus.BAD_REQUEST);
			} else {
				_response.setStatuscode(500);
				_response.setMenssage("Falha ao efetuar empréstimo, tente novamente!");
				return new ResponseEntity<>(_response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@PutMapping("/{idLoan}/return")
	public ResponseEntity<ResponseDTO> updateId(@PathVariable String id, @PathVariable String idLoan) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			Loan _loan = readLoanById(idLoan);
			_loan.setReturned(Utils.getCurrentTimeAsDate());
			_response.setLoan(loanService.save(_loan));
			Book _book = bookService.readBookById(id);
			_book = bookService.increaseAvailable(_book);
			_response.setBook(_book);
			_response.setStatuscode(200);
			_response.setMenssage("Data devolução do livro atualizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setStatuscode(404);
			_response.setMenssage("Falha ao atualizar empréstimo, não localizado!");
			return new ResponseEntity<>(_response, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<ResponseDTO> readAll(@PathVariable String id) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setLoans(loanService.readByBookId(id));
			_response.ThrowExceptionIsEmptyLoans();
			_response.setStatuscode(200);
			_response.setMenssage("Os empréstimos foram localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/not-returned")
	public ResponseEntity<ResponseDTO> readAllNotReturned(@PathVariable String id) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setLoans(loanService.readByBookId(id));
			_response.ThrowExceptionIsEmptyLoans();
			List<Loan> _loans = new ArrayList<>();
			for (Loan l : _response.getLoans()) {
				if ((l.getReturned() == null)) {
					_loans.add(l);
				}
			}
			_response.ThrowExceptionIsEmptyLoans();
			_response.setLoans(_loans);
			_response.setStatuscode(200);
			_response.setMenssage("Os empréstimos foram localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/returned")
	public ResponseEntity<ResponseDTO> readAllReturned(@PathVariable String id) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setLoans(loanService.readByBookId(id));
			_response.ThrowExceptionIsEmptyLoans();
			List<Loan> _loans = new ArrayList<>();
			for (Loan l : _response.getLoans()) {
				if (!(l.getReturned() == null)) {
					_loans.add(l);
				}
			}
			_response.setLoans(_loans);
			_response.ThrowExceptionIsEmptyLoans();
			_response.setStatuscode(200);
			_response.setMenssage("Os empréstimos foram localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{idLoan}")
	public ResponseEntity<ResponseDTO> readAll(@PathVariable String id, @PathVariable String idLoan) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setLoan(loanService.readById(idLoan));
			_response.setStatuscode(200);
			_response.setMenssage("Os empréstimos foram localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/user")
	public ResponseEntity<ResponseDTO> readId(@PathParam(value = "") String u) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setLoans(loanService.readByUser(u));
			_response.ThrowExceptionIsEmptyLoans();
			_response.setStatuscode(200);
			_response.setMenssage("O empréstimo foi localizado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/{idLoan}")
	public ResponseEntity<ResponseDTO> deleteId(@PathVariable String id, @PathVariable String idLoan) throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			readLoanById(idLoan);
			loanService.delete(idLoan);
			_response.setStatuscode(200);
			_response.setMenssage("Livro deletado com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setStatuscode(404);
			_response.setMenssage("Falha ao deletar o empréstimo, não localizado!");
			return new ResponseEntity<>(_response, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/!/delete/all")
	public ResponseEntity<ResponseDTO> deleteAll() throws Exception {
		try {
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			loanService.deleteAll();
			_response.setStatuscode(200);
			_response.setMenssage("Dados de empréstimos deletados com sucesso!");
			return new ResponseEntity<>(_response, HttpStatus.OK);
		} catch (Exception e) {
			Utils.logError(e);
			ResponseDTO _response = ResponseDTO.builderNewResponse();
			_response.setStatuscode(500);
			_response.setMenssage("Falha ao deletar os dados de empréstimos, tente novamente!");
			return new ResponseEntity<>(_response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Loan readLoanById(String idLoan) throws Exception {
		Loan _loan = loanService.readById(idLoan);
		if (_loan.getId().isEmpty()) {
			throw new Exception("Não foi localizado o empréstimo!");
		} else {
			return _loan;
		}
	}

}
