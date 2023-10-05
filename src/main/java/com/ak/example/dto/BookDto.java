package com.ak.example.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	
	
	
	
	private int id;
	private String title;
	private String isbn;
	private double price;
	private Date publicationDate;
	private List<AuthorDto> authors;

}
