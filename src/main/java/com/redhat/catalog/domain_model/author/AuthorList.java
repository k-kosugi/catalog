package com.redhat.catalog.domain_model.author;

import java.util.Collection;
import java.util.Objects;

/**
 * AuthorList レコード.
 *
 * @param value 書籍に関連した著者の一覧
 */
public record AuthorList(Iterable<Author> value) {

    /**
     * コンストラクタ.
     *
     * @param value 書籍に関連した著者の一覧
     */
    public AuthorList {
        // null チェック
        if(Objects.isNull(value)) {
            throw new IllegalArgumentException("authorList には null を設定することはできません.");
        }

        // 空の場合
        if(!value.iterator().hasNext()) {
            throw new IllegalArgumentException("authorList には要素 0 の Collection を設定することはできません.");
        }
    }

    @Override
    public String toString() {
        var strBuilder = new StringBuilder();

        strBuilder.append("AuthorList[");
        for(Author author : this.value) {
            strBuilder.append(author.toString());
        }
        strBuilder.append("]");

        return strBuilder.toString();
    }
}
