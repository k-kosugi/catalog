package com.redhat.catalog.domain_model.book.isbn13;

import java.util.Objects;

/**
 * Publication レコード.<br/>
 * <p>
 * ISBNの4番目の要素は、特定の出版社による出版物の特定の版を識別する。<br/>
 * この要素の長さは、当該出版社の予想される出版物に直接関連して変化し、最大6桁で構成されることがある。<br/>
 * 予想されるタイトル数が最も多い出版社には、最も長い出版物要素が割り当てられ、その逆も同様です。
 * ISBNの正確な長さを維持するために、空白の桁は先頭のゼロで表されます。<br/>
 *
 * @author kkosugi
 */
public record Publication(String value) {

    public static final int MAX_LENGTH = 6;

    /**
     * コンストラクタ.
     *
     * @param value ISBN の中の Publication(出版社) を示す値を設定
     */
    public Publication {
        // null チェック
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("value には null を設定することはできません.");
        }

        // 長さのチェック
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("value には長さが " + MAX_LENGTH + " より大きい値を設定することはできません.");
        }

        // 数値に変換可能か
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("value は数値のみで構成される必要があります.");
        }
    }

    /**
     * Length of BookTitleCode.
     *
     * @return length.
     */
    public int length() {
        return this.value.length();
    }

}
