package com.redhat.catalog.domain_model.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorIdTest {

    @Test
    @DisplayName("引数にnullを渡した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new AuthorId(null);
        });

        assertEquals("AuthorId には null を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("引数に負の値を設定した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new AuthorId(-1L);
        });

        assertEquals("AuthorId には負の値を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("引数に10桁以上の値を渡した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト3() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new AuthorId(12345678901L);
        });

        assertEquals("AuthorId は 10 桁未満にする必要があります.", exception.getMessage());
    }

    @Test
    @DisplayName("境界値のテスト1")
    void 正常系のテスト() {
        var authorId = new AuthorId(1234567890L);

        assertNotNull(authorId);
        assertEquals("AuthorId[value=1234567890]", authorId.toString());
    }

}