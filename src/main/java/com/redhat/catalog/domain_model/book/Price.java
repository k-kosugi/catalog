package com.redhat.catalog.domain_model.book;

/**
 * Price レコード.
 *
 * @param value 書籍の価格
 */
public record Price(int value) {

    public static int MIN_VALUE = 0;

    /**
     * コンストラクタ.
     *
     * @param value 書籍の価格
     */
    public Price {
        // 価格が 0 以下の場合 IllegalArgumentException を発生させたい
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException("Price には " + MIN_VALUE + " より小さい値を設定することはできません.");
        }
    }

}
