package com.ak.example.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ak.example.dto.AuthorDto;
import com.ak.example.dto.BookDto;
import com.ak.example.entity.Author;
import com.ak.example.entity.Book;
import com.ak.example.repo.AuthorRepo;
import com.ak.example.repo.BookRepo;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class BookManagementServiceImpl implements BookManagementService{

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private AuthorRepo authorRepo;
	@Autowired
	private ModelMapper mm;


	@Override
	public Book createBook(BookDto bookDto) throws Exception {
		Book book = mm.map(bookDto, Book.class);
		book = bookRepo.save(book);
		return book;
	}

	@Override
	public Book updateBook(BookDto bookDto, int bookid) throws Exception {
		Book book=null;
		Optional<Book> bookOpt = bookRepo.findById(bookid);
		if(!bookOpt.isPresent())
			throw new Exception("invalid bookid");

		book=bookOpt.get();
		book=mm.map(bookDto, Book.class);
		book.setId(bookid);

		return bookRepo.save(book);
	}

	@Override
	public boolean deleteBook(int bookid) throws Exception {

		bookRepo.deleteById(bookid);
		return true;

	}

	@Override
	public Book findById(int bookId) throws Exception {
		Optional<Book> bookOpt=bookRepo.findById(bookId);
		if(!bookOpt.isPresent())
			throw new Exception("invalid bookid");
		return bookOpt.get();
	}

	@Override
	public List<Book> findAll(int pageNo, int pageSize){
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		Page<Book> page= bookRepo.findAll(pageRequest);

		return page.getContent();

	}
	
	
	//author related methods  **---------------**
	@Override
	public Author create(AuthorDto authorDto) throws Exception {
		Author author= mm.map(authorDto, Author.class);
		author.setId(0);
		return authorRepo.save(author);
		
	}

	@Override
	public Author update(AuthorDto authorDto, int authorId) throws Exception {
		Author author=null;
		Optional<Author> autherOpt = authorRepo.findById(authorId);
		if(!autherOpt.isPresent())
			throw new Exception("invalid author id");

		author=autherOpt.get();
		author=mm.map(authorDto, Author.class);
		author.setId(authorId);
		return authorRepo.save(author);
	}

	@Override
	public boolean delete(int authorId) throws Exception {
		authorRepo.deleteById(authorId);
		return true;
	}

	@Override
	public List<Author> findAllAuthors(int pageNo, int pageSize) throws Exception {
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		 Page<Author> page= authorRepo.findAll(pageRequest);
		return page.getContent();
	}

	@Override
	public Author get(int authorId) throws Exception {
		Optional<Author> autherOpt = authorRepo.findById(authorId);
		if(!autherOpt.isPresent())
			throw new Exception("invalid author id");

		return autherOpt.get();

	}



}
