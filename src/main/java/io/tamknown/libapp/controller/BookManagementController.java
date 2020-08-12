package io.tamknown.libapp.controller;

import io.tamknown.libapp.model.Book;
import io.tamknown.libapp.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.lang.Integer;


@RestController
@RequestMapping("/management/api/books")
public class BookManagementController {

    private final BookService bookService;

    public BookManagementController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Book> getAllBooks() {
        return bookService.getAllbooks();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Optional<Book> getBook(@PathVariable("id") Integer id) {
        return bookService.getBook(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('book:write')")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('book:write')")
    public void updateBook(@PathVariable("id") Integer id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('book:write')")
    public void deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
    }

}
