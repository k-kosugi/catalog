package com.redhat.catalog.application_service;

import com.redhat.catalog.domain_model.book.Book;
import com.redhat.catalog.domain_model.book.isbn13.ISBN13;
import com.redhat.catalog.domain_service.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BookApplicationService {

    private BookRepository bookRepository;

    @Autowired
    public BookApplicationService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * データソースに登録されている書籍を全て検索して返却する.
     *
     * @return 書籍一覧
     */
    public Iterable<Book> findAll() {
        return this.bookRepository.findAll();
    }

    /**
     * ISBN13 で指定した書籍をデータソースから検索して返却する.
     *
     * @param isbn13 検索対象の書籍と紐づけられた ISBN13
     * @return 検索結果の書籍。見つからなかった場合は null を返却する.
     */
    public Book findByIsbn13(ISBN13 isbn13) {
        // null チェック
        if (Objects.isNull(isbn13)) {
            throw new IllegalArgumentException("isbn13 には null を設定することはできません.");
        }

        return this.bookRepository.findByIsbn13(isbn13);
    }

    /**
     * Book インスタンスを元にデータソースに登録する.
     * どのデータソースに保存するかは infrastructure 層が責務を持つ.
     *
     * @param book データソースに登録したい Book インスタンス
     */
    public void register(Book book) {
        // null チェック
        if (Objects.isNull(book)) {
            throw new IllegalArgumentException("book には null を設定することはできません.");
        }

        // ISBN13 がすでに登録されているかどうかをチェック
        var searchedBook = this.bookRepository.findByIsbn13(book.getIsbn13());
        if (!Objects.isNull(searchedBook)) {
            // 存在している場合 IllegalArgumentException を発生させる
            throw new IllegalArgumentException("登録しようとしている Book の ISBN13 はすでに登録済みです.");
        }

        // 本の登録
        this.bookRepository.register(book);
    }

}
