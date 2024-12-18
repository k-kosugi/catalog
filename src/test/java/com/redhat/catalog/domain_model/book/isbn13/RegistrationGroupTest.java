package com.redhat.catalog.domain_model.book.isbn13;

import com.redhat.catalog.domain_model.book.isbn13.RegistrationGroup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationGroupTest {

    @Test
    void 引数にnullを渡した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegistrationGroup(null);
        });

        assertEquals("value には null を設定できません.", exception.getMessage());
    }

    @Test
    void 引数に空文字を渡した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegistrationGroup("");
        });

        assertEquals("value に設定した値は整数である必要があります.", exception.getMessage());
    }

    @Test
    void 引数に数値変換できない値を渡した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
           new RegistrationGroup("000あ");
        });

        assertEquals("value に設定した値は整数である必要があります.", exception.getMessage());
    }

    @Test
    void 引数にMAX_LENGTH以上の値を渡した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegistrationGroup("123456");
        });

        assertEquals("value には長さ 5 より長い値を設定することはできません.", exception.getMessage());
    }

    @Test
    void 引数にMAX_LENGTH以内の値を渡した場合正常に生成できることを確認する1() {
        var registrationGroup = new RegistrationGroup("12345");

        assertEquals("12345", registrationGroup.value());
        assertEquals(5, registrationGroup.length());
    }

}