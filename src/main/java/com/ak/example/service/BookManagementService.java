package com.ak.example.service;

import java.util.List;

import com.ak.example.dto.AuthorDto;
import com.ak.example.dto.BookDto;
import com.ak.example.entity.Author;
import com.ak.example.entity.Book;

public interface BookManagementService {
	
	//create book
	//update book
	//delete book
	//get book
	
	public Book createBook(BookDto bookDto)throws Exception;
	public Book updateBook(BookDto bookDto,int bookid)throws Exception;
	public boolean deleteBook(int bookid)throws Exception;
	public Book findById(int bookId)throws Exception;
	public List<Book> findAll(int pageNo,int pageSize);
	
	
	//author related methods
	//create,update,delete,views
	public Author create(AuthorDto authorDto)throws Exception;
	public Author update(AuthorDto authorDto,int authorId)throws Exception;
	public boolean delete(int authorId)throws Exception;
	public List<Author> findAllAuthors(int pageNo,int pageSize)throws Exception;
	public Author get(int authorId)throws Exception;
	
}
