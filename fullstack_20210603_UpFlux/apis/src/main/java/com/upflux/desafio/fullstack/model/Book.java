package com.upflux.desafio.fullstack.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "books")
public class Book {
	@Id
	private String id;
	private String title;
	private String autor;
	private int qnttStock;
	private List<String> bookLoans;

	public void setTitle(String title) {
		if (!(title == null || title.isEmpty())) {
			this.title = title;
		}
	}

	public void setAutor(String autor) {
		if (!(autor == null || autor.isEmpty())) {
			this.autor = autor;
		}
	}
	
	public int compare(Book book1, Book book2) {
		return 0;
	}
	
}
