package com.ak.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	
	
	//name and a biography
	@Id
	@GenericGenerator(name = "authSeq" , strategy = "sequence")
	@GeneratedValue(generator = "authSeq")
	private int id;
	private String name;
	private String biography;

}
