package com.redhat.catalog.domain_model.author;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorNameTest {

    @Test
    void firstNameにnullを渡した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new AuthorName(null, "John");
        });

        assertEquals("firstName と lastName には null を設定することはできません.", exception.getMessage());
    }

    @Test
    void lastNameにnullを設定した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new AuthorName("小杉", null);
        });

        assertEquals("firstName と lastName には null を設定することはできません.", exception.getMessage());
    }

    @Test
    void 正常系のテスト() {
        var authorName = new AuthorName("研太", "小杉");
        assertNotNull(authorName);
        assertEquals("AuthorName[firstName=研太, lastName=小杉]", authorName.toString());
        assertEquals("小杉 研太", authorName.fullName());
    }

}