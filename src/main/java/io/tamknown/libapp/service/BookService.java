package io.tamknown.libapp.service;

import io.tamknown.libapp.model.Book;
import io.tamknown.libapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.lang.Integer;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllbooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(Integer id) {
        return bookRepository.findById(id);
    }

    public void updateBook(Integer id, Book bookUpdate) {
        bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookUpdate.getTitle());
                    book.setDescription(bookUpdate.getDescription());
                    book.setRating(bookUpdate.getRating());
                    book.setStudentId(bookUpdate.getStudentId());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    bookUpdate.setId(id);
                    return bookRepository.save(bookUpdate);
                });
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
