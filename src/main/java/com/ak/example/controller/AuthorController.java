package com.ak.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ak.example.auth.AuthService;
import com.ak.example.dto.AuthorDto;
import com.ak.example.entity.Author;
import com.ak.example.service.BookManagementService;

import io.swagger.v3.oas.annotations.headers.Header;

@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private BookManagementService bookManagementService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AuthService authService;

	//create,update,delete,get,getall

	@PostMapping("/create")
	public AuthorDto create(@RequestBody AuthorDto authorDto)throws Exception {

		Author author=	bookManagementService.create(authorDto);
		return mapper.map(author, AuthorDto.class);
	}
	
	@PutMapping("/update/{authorid}")
	public AuthorDto update(@RequestBody AuthorDto authorDto,@PathVariable("authorid") int authorId)throws Exception{
		
		Author author=bookManagementService.update(authorDto, authorId);
		return mapper.map(author, AuthorDto.class);
		
	}
	
	@DeleteMapping("/{authorid}")
	public Boolean delete(@PathVariable("authorid")int authorId, @RequestHeader("jwtToken")String jwtToken)throws Exception {
	 boolean valid=authService.checkPermission(jwtToken, "DELETE"); 
	 if(!valid)
		return false;
	 
		return bookManagementService.delete(authorId);
	}

	@GetMapping("/all/{pageNo}/{pageSize}")
	public List<AuthorDto> getAll(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize)throws Exception {
		List<Author> authors=bookManagementService.findAllAuthors(pageNo, pageSize);
		return authors.stream().map(auth->mapper.map(auth, AuthorDto.class)).collect(Collectors.toList());
	}
	
	
	@GetMapping("/{authorid}")
	public AuthorDto get(@PathVariable("authorid") int authorId)throws Exception {
		Author author=  bookManagementService.get(authorId);
		return mapper.map(author, AuthorDto.class);
	}
	
	@GetMapping("/generateToken")
	public String getJwtToken() {
		List<String> permissions =Arrays.asList("DELETE","UPDATE");
		return authService.setJWTTOKEN_PERMISSION(permissions);
	}
	
}















