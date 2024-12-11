package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookDtoRepository) {
        this.bookRepository = bookDtoRepository;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List <Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToDto).toList();
    }

    @Override
    public BookDto getBookById(long id) {

        Optional<Book> optBook = bookRepository.findById(id);
        Book book = optBook.orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
        return convertToDto(book);
    }

    private BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        return bookDto;
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        return book;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    @Override
    public BookDto updateBook(long id, BookDto bookDto) {

        BookDto existingBookDto = getBookById(id);

        Book book = convertToEntity(bookDto);
        book.setId(id);
        book.setTitle(book.getTitle());
        book.setAuthor(book.getAuthor());

        Book updatedBook = bookRepository.save(book);

        return convertToDto(updatedBook);
    }

    @Override
    public void deleteBookById(long id) {
        getBookById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }
}
