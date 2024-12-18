package com.redhat.catalog.infrastructure;

import com.redhat.catalog.application_service.Converter;
import com.redhat.catalog.domain_model.book.Book;
import com.redhat.catalog.domain_model.book.isbn13.ISBN13;
import com.redhat.catalog.domain_service.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookRepositoryImpl implements BookRepository {

    private final BookPostgresql bookPostgresql;

    @Autowired
    public BookRepositoryImpl(BookPostgresql bookPostgresql) {
        this.bookPostgresql = bookPostgresql;
    }

    @Override
    public Iterable<Book> findAll() {
        return Converter.convertToBookListFromBookDaoList(this.bookPostgresql.findAll());
    }

    @Override
    public Book findByIsbn13(ISBN13 isbn13) {
        // null チェック
        if (isbn13 == null) {
            throw new IllegalArgumentException("isbn13 には null を設定することはできません.");
        }

        // ISBN13 で検索
        var book = this.bookPostgresql.findById(isbn13.toString());

        // null ではない場合
        if (book.isPresent()) {
            return Converter.convertToBookFromBookDao(book.get());
        }

        return null;
    }

    @Override
    public void register(Book book) {


    }
}
