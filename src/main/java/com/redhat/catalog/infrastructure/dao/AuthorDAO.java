package com.redhat.catalog.infrastructure.dao;

import java.io.Serializable;

import com.redhat.catalog.domain_model.author.AuthorId;
import com.redhat.catalog.domain_model.author.AuthorName;
import jakarta.persistence.*;

@Entity(name = "author")
@Table(name = "author")
public class AuthorDAO implements Serializable{

    @Id
    @Column(name = "id", length = AuthorId.MAX_LENGTH)
    private Long id;

    @Column(name = "first_name", length = AuthorName.MAX_LENGTH)
    private String firstName;

    @Column(name = "last_name", length = AuthorName.MAX_LENGTH)
    private String lastName;

    public AuthorDAO() {
    }

    public AuthorDAO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}