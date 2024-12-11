package com.example.demo.service;

import com.example.demo.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    BookDto getBookById(long id);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(long id, BookDto bookDto);

    void deleteBookById(long id);

    BookDto saveBook(BookDto bookDto);
}
