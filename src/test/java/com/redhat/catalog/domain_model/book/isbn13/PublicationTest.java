package com.redhat.catalog.domain_model.book.isbn13;

import com.redhat.catalog.domain_model.book.isbn13.Publication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicationTest {

    @Test
    void 引数にnullを設定した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Publication(null);
        });

        assertEquals("value には null を設定することはできません.", exception.getMessage());
    }

    @Test
    void 引数に空文字を設定した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
           new Publication("");
        });

        assertEquals("value は数値のみで構成される必要があります.", exception.getMessage());
    }

    @Test
    void 引数にMAX_LENGTHより大きい値を設定した場合IllegalArgumentExceptionが発生することを確認する1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
           new Publication("1234567");
        });

        assertEquals("value には長さが 6 より大きい値を設定することはできません.", exception.getMessage());
    }

    @Test
    void 引数にMAX_LENGTHより大きい値を設定した場合IllegalArgumentExceptionが発生することを確認する2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Publication("123456789012345678901");
        });

        assertEquals("value には長さが 6 より大きい値を設定することはできません.", exception.getMessage());
    }

    @Test
    void 引数にMAX_LENGTHより小さい値を設定した場合正常終了することを確認する() {
        var publication = new Publication("123");

        assertEquals("123", publication.value());
        assertEquals(3, publication.length());
    }

}