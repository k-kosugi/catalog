package com.redhat.catalog.domain_model.author;

import java.util.Objects;

/**
 * 【集約】Author クラス.
 *
 * @author kkosugi
 */
public record Author(AuthorId authorId, AuthorName authorName) {

    /**
     * コンストラクタ.
     *
     * @param authorId   著者のID
     * @param authorName 著者の名前
     */
    public Author {
        // null チェック
        if (Objects.isNull(authorId) || Objects.isNull(authorName)) {
            throw new IllegalArgumentException("authorId, authorName には null を設定することはできません.");
        }
    }

    /**
     * 著者のフルネームを返却
     *
     * @return フルネーム
     */
    public String fullName() {
        return authorName.fullName();
    }

    @Override
    public String toString() {
        return "Author[" +
                "authorId=" + authorId +
                ", authorName=" + authorName +
                ']';
    }
}
