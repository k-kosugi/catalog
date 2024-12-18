package com.redhat.catalog.infrastructure.dao;

import java.io.Serializable;

import com.redhat.catalog.domain_model.book.isbn13.Registrant;
import com.redhat.catalog.domain_model.publisher.Publisher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "publisher")
@Table(name = "publisher")
public class PublisherDAO implements Serializable {

    @Id
    @Column(name = "id", length = Registrant.MAX_LENGTH)
    private String publisherId;

    @Column(name = "name", length = 1024)
    private String publisherName;

    public PublisherDAO() {
    }

    public PublisherDAO(String publisherId, String publisherName) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return this.publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

}