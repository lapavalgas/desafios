package com.upflux.desafio.fullstack.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upflux.desafio.fullstack.model.Loan;
import com.upflux.desafio.fullstack.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository LoanRepository;

	public Loan save(Loan loan) {
		return LoanRepository.save(loan);
	}

	public List<Loan> readAll() {
		return LoanRepository.findAll();
	}

	public Loan readById(String id) {
		return LoanRepository.findById(id).get();
	}

	public List<Loan> readByUser(String user) {
		return LoanRepository.findByUserContainingIgnoreCase(user);
	}

	public List<Loan> readByBookId(String bookId) {
		return LoanRepository.findByBookId(bookId);
	}

	public void delete(String id) {
		LoanRepository.deleteById(id);
	}
	
	public void deleteAll() {
		LoanRepository.deleteAll();
	}
}
