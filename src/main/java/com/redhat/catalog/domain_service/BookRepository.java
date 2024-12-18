package com.redhat.catalog.domain_service;

import com.redhat.catalog.domain_model.book.Book;
import com.redhat.catalog.domain_model.book.isbn13.ISBN13;

import java.util.Collection;

public interface BookRepository {

    Iterable<Book> findAll();
    Book findByIsbn13(ISBN13 isbn13);
    void register(Book book);

}
