package com.upflux.desafio.fullstack.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
@Document(collection = "loans")
public class Loan {
	@Id
	private String id;
	private String user;
	private String bookId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date borrowed;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date returned;

	public void setUser(String user) {
		if (!(user == null || user.isEmpty())) {
			this.user = user;
		}
	}

	public void setBookId(String bookId) {
		if (!(bookId == null || bookId.isEmpty())) {
			this.bookId = bookId;
		}
	}
}
