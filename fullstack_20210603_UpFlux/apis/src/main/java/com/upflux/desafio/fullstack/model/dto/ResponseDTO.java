package com.upflux.desafio.fullstack.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.upflux.desafio.fullstack.model.Book;
import com.upflux.desafio.fullstack.model.Loan;
import com.upflux.desafio.fullstack.utils.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class ResponseDTO {

	private int statuscode;

	private String menssage;

	private String error;

	private Date timestamp;

	private Loan loan;

	private List<Loan> loans;

	private Book book;

	private List<Book> books;

	public void ThrowExceptionIsEmptyBooks() throws Exception {
		if (this.books.isEmpty()) {
			throw new Exception("Não há dados de livros!");
		}
	}

	public void ThrowExceptionIsEmptyLoans() throws Exception {
		if (this.loans.isEmpty()) {
			throw new Exception("Não há dados de empréstimos!");
		}
	}

	public static ResponseDTO builderNewResponse() {
		return ResponseDTO.builder().timestamp(Utils.getCurrentTimeAsDate()).build();
	}

}
