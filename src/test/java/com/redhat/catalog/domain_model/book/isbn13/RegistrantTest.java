package com.redhat.catalog.domain_model.book.isbn13;

import com.redhat.catalog.domain_model.book.isbn13.Registrant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrantTest {

    @Test
    void 引数がnullの場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Registrant(null);
        });

        assertEquals("value には null を設定できません.", exception.getMessage());
    }

    @Test
    void 引数が数字ではない場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Registrant("000000あ");
        });

        assertEquals("value は数値に変換できる必要があります.", exception.getMessage());
    }

    @Test
    void 引数が空文字の場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
           new Registrant("");
        });

        assertEquals("value は数値に変換できる必要があります.", exception.getMessage());
    }

    @Test
    void 引数が1文字の場合IllegalArgumentExceptionが発生しないことを確認する() {
        var registrant = new Registrant("1");

        assertEquals("1", registrant.value());
        assertEquals(1, registrant.length());
    }

    @Test
    void 引数が3文字の場合IllegalArgumentExceptionが発生しないことを確認する() {
        var publisherCode = new Registrant("123");

        assertEquals("123", publisherCode.value());
    }

    @Test
    void 引数が6文字の場合IllegalArgumentExceptionが発生しないことを確認する() {
        var publisherCode = new Registrant("123456");

        assertEquals("123456", publisherCode.value());
    }

    @Test
    void 引数が7文字の場合IllegalArgumentExceptionが発生しないことを確認する() {
        var publisherCode = new Registrant("1234567");

        assertEquals("1234567", publisherCode.value());
    }

    @Test
    void 引数が8文字の場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Registrant("12345678");
        });

        assertEquals("value には長さが 7 より大きい値を設定することはできません.", exception.getMessage());
    }

    @Test
    void 引数に12を与えた場合lengthが2になることを確認する() {
        var publisherCode = new Registrant("12");

        assertEquals(2, publisherCode.length());
        assertEquals("12", publisherCode.value());
    }

    @Test
    void 引数に1234567を渡した場合lengthが7になることを確認する() {
        var publisherCode = new Registrant("1234567");

        assertEquals(7, publisherCode.length());
        assertEquals("1234567", publisherCode.value());
    }

    @Test
    void 引数に12345を渡した場合toStringが12345を返却することを確認する() {
        var publisherCode = new Registrant("12345");

        assertEquals(5, publisherCode.length());
        assertEquals("12345", publisherCode.value());
    }
}