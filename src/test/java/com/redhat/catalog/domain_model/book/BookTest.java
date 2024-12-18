package com.redhat.catalog.domain_model.book;

import com.redhat.catalog.domain_model.author.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    @DisplayName("isbn13がnullの場合IllgalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            var author = new Author(new AuthorId(1L), new AuthorName("研太", "小杉"));
            var collection = new ArrayList<Author>();
            collection.add(author);
            new Book(null, new Title("テスト"), new AuthorList(collection), null, null);
        });

        assertEquals("isbn13, title, authorList, publisherName, price には null を設定することはできません.", exception.getMessage());
    }


}