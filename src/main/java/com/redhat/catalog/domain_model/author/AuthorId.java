package com.redhat.catalog.domain_model.author;

import java.util.Objects;

/**
 * AuthorId レコード.
 *
 * @param value 著者を一意に特定するための Id.
 */
public record AuthorId(Long value) {

    public static final int MAX_LENGTH = 10;

    /**
     * コンストラクタ.
     *
     * @param value 著者を一意に特定するための Id.
     */
    public AuthorId {
        // null チェック
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("AuthorId には null を設定することはできません.");
        }

        // 負の値チェック
        if(value < 0){
            throw new IllegalArgumentException("AuthorId には負の値を設定することはできません.");
        }

        // MAX_LENGTH より大きい場合
        if(String.valueOf(value).length() > MAX_LENGTH) {
            throw new IllegalArgumentException("AuthorId は " + MAX_LENGTH + " 桁未満にする必要があります.");
        }

        // 桁数チェック
        var strValue = new StringBuilder(value.toString());
        if (strValue.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("AuthorId は 10 桁未満にする必要があります.");
        }
    }

    @Override
    public String toString() {
        return "AuthorId[" +
                "value=" + value +
                "]";
    }
}
