package com.redhat.catalog.domain_model.publisher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherNameTest {

    @Test
    @DisplayName("nullを渡した場合はIllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
           new PublisherName(null);
        });

        assertEquals("PublisherName には null を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("空文字を渡した場合はIllegalArgumentExceptionが発生することを確認する")
    void コンストラクタのテスト2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new PublisherName("");
        });

        assertEquals("PublisherName には空文字を設定することはできません.", exception.getMessage());
    }

    @Test
    @DisplayName("正常系のテスト")
    void コンストラクタのテスト3() {
        var publisherName = new PublisherName("株式会社インプレス");
        assertNotNull(publisherName);
        assertEquals("株式会社インプレス", publisherName.value());
        assertEquals("PublisherName[value=株式会社インプレス]", publisherName.toString());
    }

}