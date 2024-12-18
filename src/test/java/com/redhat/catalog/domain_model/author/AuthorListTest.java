package com.redhat.catalog.domain_model.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AuthorListTest {

    @Test
    @DisplayName("引数にnullを渡した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new AuthorList(null);
        });

        assertEquals("authorList には null を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("引数に要素0のCollectionを渡した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
           new AuthorList(new ArrayList<>());
        });

        assertEquals("authorList には要素 0 の Collection を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("正常系のテスト1")
    void 正常系のテスト1() {
        var collection = new ArrayList<Author>();
        collection.add(new Author(new AuthorId(1L), new AuthorName("研太", "小杉")));
        collection.add(new Author(new AuthorId(2L), new AuthorName("研太", "小杉")));

        var authorList = new AuthorList(collection);

        assertEquals("AuthorList[Author[authorId=AuthorId[value=1], authorName=AuthorName[firstName=研太, lastName=小杉]]Author[authorId=AuthorId[value=2], authorName=AuthorName[firstName=研太, lastName=小杉]]]", authorList.toString());
    }
}