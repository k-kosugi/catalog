package com.redhat.catalog.domain_model.publisher;

import com.redhat.catalog.domain_model.book.isbn13.Publication;
import com.redhat.catalog.domain_model.book.isbn13.Registrant;

import java.util.Objects;

/**
 * 【集約】Publisher クラス.
 *
 * @author kkosugi
 */
public record Publisher(Registrant registrant, PublisherName publisherName) {

    /**
     * コンストラクタ.
     *
     * @param registrant   出版社の ID を表す Registrant インスタンス
     * @param publisherName 出版社の名前
     * @see Publication
     */
    public Publisher {
        // null チェック
        if(Objects.isNull(registrant) || Objects.isNull(publisherName)) {
            throw new IllegalArgumentException("Publication, PublisherName には null を設定することはできません.");
        }

    }

    @Override
    public String toString() {
        return "Publisher[" +
                "registrant=" + this.registrant +
                ", publisherName=" + this.publisherName +
                ']';
    }
}
