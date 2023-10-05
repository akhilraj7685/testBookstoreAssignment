package com.ak.example.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	
	//title, ISBN, description, price, publication date, and a list of authors
	@Id
	@GenericGenerator(name = "bookSeq" , strategy = "sequence")
	@GeneratedValue(generator = "bookSeq")
	private int id;
	private String title;
	private String isbn;
	private double price;
	private Date publicationDate;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Author> authors=new ArrayList<Author>();

}
