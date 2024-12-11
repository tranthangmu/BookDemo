package com.example.demo.rest;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") long id) {
        try{
            BookDto bookDto = bookService.getBookById(id);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") long id, @RequestBody BookDto bookDto) {
        try {
            BookDto updatedBookDto = bookService.updateBook(id, bookDto);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById (@PathVariable("id") long id) {
        bookService.deleteBookById(id);

        return new ResponseEntity<String>("Book with id: " + id + " was deleted successfully.", HttpStatus.OK);
    }

}
