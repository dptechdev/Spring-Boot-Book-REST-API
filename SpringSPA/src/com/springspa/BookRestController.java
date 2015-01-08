package com.springspa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
	
	private BookRepository BookRepo;
	
	@Autowired
	public BookRestController(BookRepository BookRepo) {
		this.BookRepo = BookRepo;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Book create(@RequestBody @Valid Book book) {
		return this.BookRepo.save(book);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<Book> list() {
		return this.BookRepo.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Book get(@PathVariable("id") int id) {
		return this.BookRepo.findOne(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Book update(@PathVariable("id") int id, @RequestBody @Valid Book book) {
		return BookRepo.save(book);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
		this.BookRepo.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

}
