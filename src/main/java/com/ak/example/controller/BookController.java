package com.ak.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ak.example.dto.BookDto;
import com.ak.example.entity.Book;
import com.ak.example.service.BookManagementService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookManagementService bookManagementService;
	@Autowired
	private ModelMapper modelMapper;

	//create book


	@PostMapping("/create")
	public BookDto createBook(@RequestBody BookDto bookDto) {

		Book book;
		try {
			book = bookManagementService.createBook(bookDto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return modelMapper.map(book, BookDto.class);
	}


	//update book
	@PostMapping("/edit/{bookid}")
	public BookDto updateBook(@RequestBody BookDto bookDto,@PathVariable("bookid") int bookId)throws Exception {
		Book book=	bookManagementService.updateBook(bookDto, bookId);
		return modelMapper.map(book, BookDto.class);
	}

	//get all books
	@GetMapping("/all/{pageNo}/{pageSize}")
	public List<BookDto> getAllBooks(@PathVariable("pageNo")int pageNo, @PathVariable("pageSize")int pageSize){
		List<Book> books=	bookManagementService.findAll(pageNo, pageSize);

		return books.stream().map(bk->modelMapper.map(bk, BookDto.class)).collect(Collectors.toList());

	}

	@GetMapping("/{bookid}")
	public BookDto findById(@PathVariable("bookid") int bookId) throws Exception {
		Book book=bookManagementService.findById(bookId);
		return modelMapper.map(book, BookDto.class);
	}

	@DeleteMapping("/remove/{bookId}")
	public Boolean deleteBookById(@PathVariable("bookId")int bookId) throws Exception {
		return	bookManagementService.deleteBook(bookId);
	}




}
