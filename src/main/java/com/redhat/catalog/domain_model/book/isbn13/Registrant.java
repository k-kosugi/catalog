package com.redhat.catalog.domain_model.book.isbn13;

import java.util.Objects;

/**
 * Registrant レコード. <br/>
 *
 * ISBNの第3の要素は、登録グループ内の特定の出版社または版元を識別する。<br/>
 * この要素の長さは、出版者の予想される生産量に直接関係して変化し、最大7桁で構成されることがある。<br/>
 * 予想されるタイトル数が最大の出版社には、最も短い登録者要素が割り当てられ、その逆も同様である。<br/>
 * 潜在的な登録者は、ISBNシステムの管理を担当するISBN登録機関に申請し、その国、地域、または言語グループの中で、その登録者に固有の登録者要素を割り当てる。<br/>
 * 登録者は、その登録者要素にリンクされたISBNの割り当てを使い切った後、次のことを実施可能。</br>
 * ISBNのさらなる割り当てを提供する追加の登録者要素を割り当てできる。
 *
 * @author kkosugi
 */
public record Registrant(String value) {

    public static final int MAX_LENGTH = 7;

    /**
     * コンストラクタ.
     *
     * @param value ISBN13 の中で出版社コードを示す数値を String 型で指定
     */
    public Registrant {
        // null チェック.
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("value には null を設定できません.");
        }

        // 長さのチェック
        var lengthOfPublisherCode = value.length();
        if (lengthOfPublisherCode > MAX_LENGTH) {
            throw new IllegalArgumentException("value には長さが " + MAX_LENGTH + " より大きい値を設定することはできません.");
        }

        // 数値に変換できない場合はエラー.
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("value は数値に変換できる必要があります.");
        }
    }

    /**
     * 長さを返却.
     *
     * @return publisherCode の文字列長を返却
     */
    public int length() {
        return this.value.length();
    }

}