package com.redhat.catalog.domain_model.book.isbn13;

import com.redhat.catalog.domain_model.book.isbn13.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISBN13Test {

    @Test
    @DisplayName("gs1にnullを設定した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13(null, new RegistrationGroup("4"), new Registrant("333"), new Publication("111"));
        });

        assertEquals("gs1 には null を設定できません.", exception.getMessage());
    }

    @Test
    @DisplayName("registrationGroupにnullを設定した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13(GS1.ISBN978, null, new Registrant("333"), new Publication("111"));
        });

        assertEquals("registrationGroup には null を設定できません.", exception.getMessage());
    }

    @Test
    @DisplayName("registrantにnullを設定した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト3() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13(GS1.ISBN978, new RegistrationGroup("4"), null, new Publication("111"));
        });

        assertEquals("registrant には null を設定できません.", exception.getMessage());
    }

    @Test
    @DisplayName("publicationにnullを設定した場合IllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト4() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13(GS1.ISBN978, new RegistrationGroup("4"), new Registrant("456"), null);
        });

        assertEquals("publication には null を設定できません.", exception.getMessage());
    }

    @Test
    @DisplayName("ISBN13の文字列長が13未満の場合にIllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト5() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13(GS1.ISBN978, new RegistrationGroup("4"), new Registrant("456"), new Publication("1234"));
        });

        assertEquals("ISBN13 を構成する各要素の長さの合計はチェックデジットを除いて 12 になる必要があります.", exception.getMessage());
    }

    @Test
    @DisplayName("ISBN13の文字列長が13より大きい場合にIllegalArgumentExceptionが発生することを確認する1")
    void コンストラクタのテスト6() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13(GS1.ISBN978, new RegistrationGroup("4"), new Registrant("456"), new Publication("123456"));
        });

        assertEquals("ISBN13 を構成する各要素の長さの合計はチェックデジットを除いて 12 になる必要があります.", exception.getMessage());
    }

    @Test
    @DisplayName("ISBN13の文字列長が13より大きい場合にIllegalArgumentExceptionが発生することを確認する2")
    void コンストラクタのテスト7() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13(GS1.ISBN978, new RegistrationGroup("12345"), new Registrant("4567"), new Publication("123456"));
        });

        assertEquals("ISBN13 を構成する各要素の長さの合計はチェックデジットを除いて 12 になる必要があります.", exception.getMessage());
    }

    @Test
    @DisplayName("引数にnullを渡した場合IllegalArgumentExceptionが発生することを確認する.")
    void コンストラクタのテスト8() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13(null);
        });

        assertEquals("strIsbn13 には null を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("引数に空文字を渡した場合にIllegalArgumentExceptionが発生することを確認する.")
    void コンストラクタのテスト9() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13("");
        });

        assertEquals("strIsbn13 の文字列長は 17 である必要があります.", exception.getMessage());
    }

    @Test
    @DisplayName("チェックデジットが間違っている値の場合IllegalArgumentExceptionが発生することを確認する.")
    void コンストラクタのテスト10() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13("ISBN 978-4-87311-758-5");
        });

        assertEquals("チェックデジットの値が一致しません.", exception.getMessage());
    }

    @Test
    @DisplayName("Separator で分離した要素が 5 以外の場合 IllegalArgumentException が発生することを確認する")
    void コンストラクタのテスト11() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new ISBN13("ISBN 978-4-87-11-758-5");
        });

        assertEquals("strIsbn13 は '-' で分離した要素の数が 5 である必要があります.", exception.getMessage());
    }

    @Test
    @DisplayName("ISBN978 の場合の正常系")
    void コンストラクタのテスト12() {
        var isbn13 = new ISBN13("ISBN 978-4-87311-758-4");
        assertEquals("978", isbn13.getGs1().toString());
        assertEquals("4", isbn13.getRegistrationGroup().value());
        assertEquals("87311", isbn13.getRegistrant().value());
        assertEquals("758", isbn13.getPublication().value());
    }

    @Test
    @DisplayName("ISBN979 の場合の正常系")
    void コンストラクタのテスト13() {
        var isbn13 = new ISBN13("ISBN 979-4-87311-758-3");
        assertEquals("979", isbn13.getGs1().toString());
        assertEquals("4", isbn13.getRegistrationGroup().value());
        assertEquals("87311", isbn13.getRegistrant().value());
        assertEquals("758", isbn13.getPublication().value());
    }

    @Test
    @DisplayName("正常系のテスト")
    void 正常系1() {
        //978-4-87311-758-4
        var isbn = new ISBN13(GS1.ISBN978, new RegistrationGroup("4"), new Registrant("87311"), new Publication("758"));
        assertEquals("ISBN 978-4-87311-758-4", isbn.toString());
        assertEquals(4, isbn.checkDigit());
    }

    @Test
    @DisplayName("正常系のテスト(コンストラクタの引数が String 型)")
    void 正常系2() {
        //978-4-87311-758-4
        var isbn = new ISBN13("ISBN 978-4-87311-758-4");
        assertEquals("ISBN 978-4-87311-758-4", isbn.toString());
        assertEquals(4, isbn.checkDigit());
    }


    @Test
    @DisplayName("正常系のテスト(コンストラクタの引数が String 型)")
    void 正常系3() {
        //978-4-87311-758-4
        var isbn = new ISBN13(GS1.ISBN978, new RegistrationGroup("4"), new Registrant("295"), new Publication("00552"));
        assertEquals("ISBN 978-4-295-00552-0", isbn.toString());
        assertEquals(0, isbn.checkDigit());
    }
}