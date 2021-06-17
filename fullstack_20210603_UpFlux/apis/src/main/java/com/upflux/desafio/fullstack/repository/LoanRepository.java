package com.upflux.desafio.fullstack.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.upflux.desafio.fullstack.model.Loan;

public interface LoanRepository extends MongoRepository<Loan, String> {

	List<Loan> findByUserContainingIgnoreCase(String user);

	List<Loan> findByBookId(String bookId);

}