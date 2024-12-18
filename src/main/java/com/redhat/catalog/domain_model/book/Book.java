package com.redhat.catalog.domain_model.book;

import com.redhat.catalog.domain_model.author.AuthorList;
import com.redhat.catalog.domain_model.book.isbn13.ISBN13;
import com.redhat.catalog.domain_model.publisher.Publisher;
import com.redhat.catalog.domain_model.publisher.PublisherName;

import java.util.Objects;

/**
 * 【集約】Book クラス.
 *
 * @author kkosugi.
 */
public final class Book {

    private final ISBN13 isbn13;

    private final Title title;

    private final AuthorList authorList;

    private final Publisher publisher;

    private final Price price;

    /**
     * コンストラクタ.
     *
     * @param isbn13        書籍に関連づけられた ISBN13.
     * @param title         書籍のタイトル
     * @param authorList    書籍の著者のリスト
     * @param publisherName 出版社
     * @param price         価格
     */
    public Book(final ISBN13 isbn13, final Title title, final AuthorList authorList, final PublisherName publisherName,
                final Price price) {

        // null チェック
        var isNull = Objects.isNull(isbn13) || Objects.isNull(authorList) || Objects.isNull(publisherName) || Objects.isNull(price);
        if (isNull) {
            throw new IllegalArgumentException("isbn13, title, authorList, publisherName, price には null を設定することはできません.");
        }

        this.isbn13 = isbn13;
        this.title = title;
        this.authorList = authorList;
        this.price = price;

        // isbn の publication をそのまま利用する
        this.publisher = new Publisher(isbn13.getRegistrant(), publisherName);
    }

    /**
     * ISBN13 の値を取得する.
     *
     * @return isbn13
     */
    public ISBN13 getIsbn13() {
        return this.isbn13;
    }

    /**
     * title を取得する.
     *
     * @return title
     */
    public Title getTitle() {
        return this.title;
    }

    /**
     * authorList を取得する.
     *
     * @return authorList
     */
    public AuthorList getAuthorList() {
        return this.authorList;
    }

    /**
     * Publisher を取得する.
     *
     * @return publisher
     */
    public Publisher getPublisher() {
        return this.publisher;
    }

    /**
     * Price を取得する.
     *
     * @return price.
     */
    public Price getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "ISBN:" + this.isbn13.toString()
                + "Title:" + this.title.value()
                + "Author:" + this.authorList.value()
                + "Publisher:" + this.publisher.toString()
                + "Price:" + this.price.toString();
    }
}
