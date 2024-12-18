package com.redhat.catalog.infrastructure.dao;

import com.redhat.catalog.domain_model.book.Title;
import com.redhat.catalog.domain_model.book.isbn13.GS1;
import com.redhat.catalog.domain_model.book.isbn13.ISBN13;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity(name = "book")
@Table(name = "book")
@NamedQuery(name = "findAllBooks", query = "select a from book a")
@NamedQuery(name = "findBookByIsbn", query = "select a from book a where a.isbn13 = :isbn")
@NamedQuery(name = "findBooksByTitle", query = "select a from book a where upper(a.title) like upper(:title)")
public class BookDAO implements Serializable {

    @Id
    @Column(name = "isbn13", length = ISBN13.MAX_LENGTH)
    private String isbn13;

    @Column(name = "title", length = Title.MAX_LENGTH)
    private String title;

    @Column(name = "price")
    private int price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author", joinColumns = @JoinColumn(name = "book_isbn13", referencedColumnName = "isbn13"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id", unique = false)
    )
    private Collection<AuthorDAO> authorList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", unique = false)
    private PublisherDAO publisher;

    /**
     * 引数なしのコンストラクタ.<br/>
     * ORM を利用するのに必要.
     */
    public BookDAO() {
    }

    /**
     * コンストラクタ.
     *
     * @param isbn13     book テーブルの isbn13 列
     * @param title      book テーブルの title 列
     * @param price      book テーブルの price 列
     * @param authorList book テーブルの isbn13 に関連づけられた Author の一覧
     * @param publisher  book テーブルの isbn13 に関連づけれれた Publisher
     */
    public BookDAO(String isbn13, String title, int price, Collection<AuthorDAO> authorList, PublisherDAO publisher) {
        this.isbn13 = isbn13;
        this.title = title;
        this.price = price;
        this.authorList = authorList;
        this.publisher = publisher;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Iterable<AuthorDAO> getAuthorList() {
        return this.authorList;
    }

    public void setAuthorList(Collection<AuthorDAO> authorList) {
        this.authorList = authorList;
    }

    public PublisherDAO getPublisher() {
        return this.publisher;
    }

    public void setPublisher(PublisherDAO publisher) {
        this.publisher = publisher;
    }
}