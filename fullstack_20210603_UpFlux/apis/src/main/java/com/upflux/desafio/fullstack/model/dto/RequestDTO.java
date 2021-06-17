package com.upflux.desafio.fullstack.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.upflux.desafio.fullstack.model.Book;
import com.upflux.desafio.fullstack.model.Loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class RequestDTO {

	private Book book;

	private Loan loan;

	public Book builderNewBook() {
		return Book.builder().title(this.book.getTitle()).autor(this.book.getAutor()).qnttStock(this.book.getQnttStock()).build();
	}

	public Loan builderNewLoan() {
		return Loan.builder().user(this.loan.getUser()).bookId(this.loan.getBookId()).borrowed(this.loan.getBorrowed()).build();
	}
}
