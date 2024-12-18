package com.redhat.catalog.application_service;

import com.redhat.catalog.domain_model.author.Author;
import com.redhat.catalog.domain_model.author.AuthorId;
import com.redhat.catalog.domain_model.author.AuthorList;
import com.redhat.catalog.domain_model.author.AuthorName;
import com.redhat.catalog.domain_model.book.Book;
import com.redhat.catalog.domain_model.book.Price;
import com.redhat.catalog.domain_model.book.Title;
import com.redhat.catalog.domain_model.book.isbn13.*;
import com.redhat.catalog.domain_model.publisher.PublisherName;
import com.redhat.catalog.infrastructure.dao.AuthorDAO;
import com.redhat.catalog.infrastructure.dao.BookDAO;
import com.redhat.catalog.infrastructure.dao.PublisherDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Converter クラス.
 *
 * @author kkosugi
 */
public final class Converter {

    private Converter() {
    }

    /**
     * BookDAO インスタンスを Book インスタンスに変換して返却する.
     *
     * @param bookDao 変換したい BookDAO インスタンス
     * @return 変換後の Book インスタンス
     */
    public static Book convertToBookFromBookDao(BookDAO bookDao) {
        // null チェック
        if (Objects.isNull(bookDao)) {
            throw new IllegalArgumentException("bookDAO には null を設定することはできません.");
        }

        // BookDAO から Book を生成して返却.
        return new Book(new ISBN13(bookDao.getIsbn13()), new Title(bookDao.getTitle()),
                convertToAuthorFromAuthorDaoList(bookDao.getAuthorList()),
                new PublisherName(bookDao.getPublisher().getPublisherName()),
                new Price(bookDao.getPrice()));
    }

    /**
     * Iterable<BookDAO> インスタンスを Iterable<Book> に変換して返却します.
     *
     * @param bookDaoList 変換したい Iterable<BookDAO> インスタンス
     * @return 変換後の Iterable<Book> インスタンス
     */
    public static Iterable<Book> convertToBookListFromBookDaoList(Iterable<BookDAO> bookDaoList) {
        // null チェック
        if (Objects.isNull(bookDaoList)) {
            throw new IllegalArgumentException("bookDaoList には null を設定することはできません.");
        }

        // 要素 0 の場合
        if (!bookDaoList.iterator().hasNext()) {
            throw new IllegalArgumentException("bookDaoList には要素 0 の Iterable を設定することはできません.");
        }

        // 戻り値の確保
        var bookList = new ArrayList<Book>();

        // Iterator の取得
        var iterator = bookDaoList.iterator();
        while (iterator.hasNext()) {
            var bookDao = iterator.next();
            bookList.add(convertToBookFromBookDao(bookDao));
        }

        // 返却
        return bookList;
    }

    public static Iterable<BookDAO> convertToBookDaoListFromBookList(Iterable<Book> bookList) {
        // null チェック
        if (Objects.isNull(bookList)) {
            throw new IllegalArgumentException("bookList には null を設定することはできません");
        }

        // 要素 0 の場合
        if(!bookList.iterator().hasNext()) {
            throw new IllegalArgumentException("bookList には要素 0 の Iterable を設定することはできません.");
        }

        // 戻り値の返却
        var bookDAOList = new ArrayList<BookDAO>();
        for(var book : bookList) {
            var bookDao = new BookDAO();
            bookDao.setIsbn13(book.getIsbn13().toString());
            bookDao.setTitle(book.getTitle().value());
            bookDao.setPrice(book.getPrice().value());

            // publisher
            var publisherDao = new PublisherDAO();
            publisherDao.setPublisherId(book.getPublisher().registrant().value());
            publisherDao.setPublisherName(book.getPublisher().publisherName().value());
            bookDao.setPublisher(publisherDao);

            // author
            bookDao.setAuthorList((Collection<AuthorDAO>) Converter.convertAuthorDaoListFromAuthorList(book.getAuthorList()));

            // 要素の追加
            bookDAOList.add(bookDao);
        }

        return bookDAOList;
    }

    /**
     * Iterable<AuthorDAO> を AuthorList インスタンスに変換して返却する.
     *
     * @param authorDaoList 変換したい Iterable<AuthorDAO> インスタンス
     * @return 変換後の AuthorList インスタンス
     */
    public static AuthorList convertToAuthorFromAuthorDaoList(Iterable<AuthorDAO> authorDaoList) {
        // null チェック
        if (Objects.isNull(authorDaoList)) {
            throw new IllegalArgumentException("authorDaoList には null を設定することはできません.");
        }

        // 要素 0 の場合
        if (!authorDaoList.iterator().hasNext()) {
            throw new IllegalArgumentException("authorDaoList には要素 0 の Iterable を設定することはできません.");
        }

        // 戻り値の確保
        Collection<Author> authors = new ArrayList<>();

        for (AuthorDAO authorDao : authorDaoList) {
            // Collection への追加
            authors.add(new Author(
                    new AuthorId(authorDao.getId()),    // authorId
                    new AuthorName(authorDao.getFirstName(), authorDao.getLastName()) // authorName
            ));
        }

        // 返却
        return new AuthorList(authors);
    }

    /**
     * Book インスタンスを BookDAO インスタンスに変換して返却する.
     *
     * @param book 変換したい Book インスタンス
     * @return 変換済みの BookDAO インスタンス
     */
    public static BookDAO convertToBookDaoFromBook(Book book) {
        // null チェック
        if (Objects.isNull(book)) {
            throw new IllegalArgumentException("book には null を設定することはできません.");
        }

        // 戻り値用オブジェクト
        var bookDao = new BookDAO();
        bookDao.setIsbn13(book.getIsbn13().toString());
        bookDao.setTitle(book.getTitle().toString());
        bookDao.setAuthorList((Collection<AuthorDAO>) convertAuthorDaoListFromAuthorList(book.getAuthorList()));
        bookDao.setPrice(book.getPrice().value());


        return bookDao;
    }

    /**
     * Iterable<AuthorDAO> インスタンスを AuthorList インスタンスに変換して返却します.
     *
     * @param authorDaoList 変換したい Iterable<AuthorDAO> インスタンス
     * @return 変換後の AuthorList インスタンス
     */
    public static AuthorList convertToAuthorListFromAuthorDaoList(Iterable<AuthorDAO> authorDaoList) {
        // null チェック
        if (Objects.isNull(authorDaoList)) {
            throw new IllegalArgumentException("authorDaoList には null を設定することはできません.");
        }

        // 要素 0 の場合
        if (!authorDaoList.iterator().hasNext()) {
            throw new IllegalArgumentException("authorDaoList には要素 0 の Iterable を設定することはできません.");
        }

        // AuthorList を作成するための List を作成
        var collection = new ArrayList<Author>();
        for (AuthorDAO authorDao : authorDaoList) {
            // 要素の追加
            collection.add(new Author(
                    new AuthorId(authorDao.getId()),
                    new AuthorName(authorDao.getFirstName(), authorDao.getLastName()
                    )));
        }

        // 返却
        return new AuthorList(collection);
    }

    /**
     * Iterable<Author> インスタンスを Iterable<AuthorDAO> に変換して返却する.
     *
     * @param authorDaoList 変換したい AuthorList インスタンス
     * @return 変換後の Iterable<AuthorDAO> インスタンス
     */
    public static Iterable<Author> convertAuthorListFromAuthorDaoList(Iterable<AuthorDAO> authorDaoList) {
        // null チェック
        if (Objects.isNull(authorDaoList)) {
            throw new IllegalArgumentException("authorDaoList には null を設定することはできません.");
        }

        // 要素 0 の場合
        if (!authorDaoList.iterator().hasNext()) {
            throw new IllegalArgumentException("authorDaoList には要素 0 の Iterable を設定することはできません.");
        }

        // 戻り値用変数確保
        ArrayList<Author> authors = new ArrayList<>();
        for (AuthorDAO authorDao : authorDaoList) {
            // 要素の追加
            authors.add(new Author(
                    new AuthorId(authorDao.getId()),
                    new AuthorName(authorDao.getFirstName(), authorDao.getLastName())
            ));
        }

        // 返却
        return authors;
    }

    /**
     * Iterable<AuthorDAO> インスタンスを Iterable<Author> に変換して返却する.
     *
     * @param authorList 変換したい AuthorList インスタンス
     * @return 変換後の Iterable<AuthorDAO> インスタンス
     */
    public static Iterable<AuthorDAO> convertAuthorDaoListFromAuthorList(AuthorList authorList) {
        // null チェック
        if (Objects.isNull(authorList)) {
            throw new IllegalArgumentException("authorList には null を設定することはできません.");
        }

        // 戻り値の生成
        var returnObject = new ArrayList<AuthorDAO>();

        for (Author author : authorList.value()) {
            AuthorDAO authorDao = new AuthorDAO();

            authorDao.setId(author.authorId().value());
            authorDao.setFirstName(author.authorName().firstName());
            authorDao.setLastName(author.authorName().lastName());

            // 要素の追加
            returnObject.add(authorDao);
        }

        // 返却
        return returnObject;
    }

}
