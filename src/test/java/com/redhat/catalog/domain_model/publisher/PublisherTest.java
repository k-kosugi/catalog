package com.redhat.catalog.domain_model.publisher;

import com.redhat.catalog.domain_model.book.isbn13.Publication;
import com.redhat.catalog.domain_model.book.isbn13.Registrant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherTest {

    @Test
    void publicationにnullを設定した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Publisher(null, new PublisherName("テスト"));
        });

        assertEquals("Publication, PublisherName には null を設定することはできません.", exception.getMessage());
    }

    @Test
    void publisherNameにnullを設定した場合IllegalArgumentExceptionが発生することを確認する() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new Publisher(new Registrant("443"), null);
        });

        assertEquals("Publication, PublisherName には null を設定することはできません.", exception.getMessage());
    }

    @Test
    void 正常系() {
        var publisher = new Publisher(new Registrant("4444"), new PublisherName("テスト"));
        assertNotNull(publisher);
        assertEquals("Publisher[registrant=Registrant[value=4444], publisherName=PublisherName[value=テスト]]", publisher.toString());
    }
}