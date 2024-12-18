package com.redhat.catalog.domain_model.publisher;

import java.util.Objects;

/**
 * PublisherName レコード
 *
 * @param value 出版社名
 */
public record PublisherName(String value) {

    public static final int MAX_LENGTH = 1024;

    /**
     * コンストラクタ.
     *
     * @param value 出版社名
     */
    public PublisherName {
        // null チェック
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("PublisherName には null を設定することはできません.");
        }

        // 空文字
        if (value.isEmpty()) {
            throw new IllegalArgumentException("PublisherName には空文字を設定することはできません.");
        }
    }
}
