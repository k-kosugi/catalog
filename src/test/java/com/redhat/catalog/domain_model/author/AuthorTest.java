package com.redhat.catalog.domain_model.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    @DisplayName("authorIdにnullを渡した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Author(null, new AuthorName("小杉", "研太"));
        });

        assertEquals("authorId, authorName には null を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("authorNameにnullを渡した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Author(new AuthorId(345L), null);
        });

        assertEquals("authorId, authorName には null を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("正常系のテスト1")
    void 正常系のテスト1() {
        var author = new Author(
                new AuthorId(345L),
                new AuthorName("研太", "小杉")
        );

        assertNotNull(author);
        assertEquals("小杉 研太", author.fullName());
        assertEquals("Author[authorId=AuthorId[value=345], authorName=AuthorName[firstName=研太, lastName=小杉]]", author.toString());
    }

}