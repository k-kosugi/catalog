package com.redhat.catalog.domain_model.book;

import java.util.Objects;

/**
 * Title レコード.
 *
 * @param value 書籍に設定するタイトルを指定する.
 */
public record Title(String value) {

    public static final int MAX_LENGTH = 2048;

    /**
     * コンストラクタ
     *
     * @param value 書籍のタイトル.
     */
    public Title {
        // null チェック
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("value には null を設定することはできません.");
        }

        // 空文字チェック
        if (value.isEmpty()) {
            throw new IllegalArgumentException("value には空文字を設定することはできません.");
        }

        // 長さチェック
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("value の長さは " + MAX_LENGTH + " より大きく設定することはできません.");
        }
    }
}
