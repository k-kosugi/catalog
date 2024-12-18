package com.redhat.catalog.domain_model.book.isbn13;

import java.util.Objects;

/**
 * RegistrationGroup レコード. <br/>
 * ISBNの2番目の要素は、ISBNシステムに参加している国、地理的地域、言語圏を示す。<br/>
 * ISBNシステムのメンバーには、言語圏を形成するものもあれば
 * (例：登録グループ番号3 = GS1要素978内のドイツ言語グループ)、地域単位を形成するものもあります(例：登録グループ番号982 = GS1要素978内の南太平洋地域)。
 * この要素の長さはさまざまですが、最大5桁で構成されることがあります。登録グループ要素は、国際ISBN機関によって割り当てられます。<br/>
 * <em>Example : 978-92</em>
 *
 * @author kkosugi
 */
public record RegistrationGroup(String value) {

    public static final int MAX_LENGTH = 5;

    /**
     * コンストラクター.
     *
     * @param value 出版物の出版された国、地域、言語圏。
     */
    public RegistrationGroup {
        // null チェック
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("value には null を設定できません.");
        }

        // 長さチェック
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("value には長さ " + MAX_LENGTH + " より長い値を設定することはできません.");
        }

        // Integer に parse できない場合、エラーを発生させる
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("value に設定した値は整数である必要があります.");
        }
    }

    /**
     * RegistrationGroup として設定した値の長さ
     *
     * @return The length of GroupNo.
     */
    public int length() {
        return this.value.length();
    }
}
