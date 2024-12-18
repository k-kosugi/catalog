package com.redhat.catalog.domain_model.author;

import java.util.Objects;

/**
 * Author レコード.
 *
 * @param firstName 著者の姓
 * @param lastName  著者の名
 */
public record AuthorName(String firstName, String lastName) {

    public static final int MAX_LENGTH = 1024;

    public static final String SEPARATOR = " ";

    /**
     * コンストラクタ
     *
     * @param firstName 著者の姓
     * @param lastName  著者の名
     */
    public AuthorName {
        // null チェック
        if (Objects.isNull(firstName) || Objects.isNull(lastName)) {
            throw new IllegalArgumentException("firstName と lastName には null を設定することはできません.");
        }

        // 空文字
        if(firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("firstName と lastName には空文字を設定することはできません.");
        }

        // 長さチェック
        if(firstName.length() > MAX_LENGTH || lastName.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("firstName と lastName は長さ '" + MAX_LENGTH + " 'を超えることはできません.");
        }
    }

    /**
     * 著者のフルネームを firstName, lastName から返却します.
     *
     * @return 著者のフルネーム
     */
    protected String fullName() {
        return this.lastName + SEPARATOR + this.firstName;
    }

    @Override
    public String toString() {
        return "AuthorName[" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ']';
    }
}
