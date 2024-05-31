package com.hutech.NguyenDacThang.services;

import com.hutech.NguyenDacThang.entities.Book;
import com.hutech.NguyenDacThang.repositories.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE,
        rollbackFor = {Exception.class, Throwable.class})
public class BookService {
    private final IBookRepository bookRepository;

    public List<Book> getAllBooks(Integer pageNo,
                                  Integer pageSize,
                                  String sortBy) {

        return bookRepository.findAllBooks(pageNo, pageSize, sortBy);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(@NotNull Book book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElse(null);
        Objects.requireNonNull(existingBook).setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setCategory(book.getCategory());
        bookRepository.save(existingBook);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return bookRepository.findAll();
        } else {
            return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(searchTerm, searchTerm);
        }
    }
}
