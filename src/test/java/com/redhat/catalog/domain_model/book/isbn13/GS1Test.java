package com.redhat.catalog.domain_model.book.isbn13;

import com.redhat.catalog.domain_model.book.isbn13.GS1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GS1Test {

    @Test
    void ISBN978を指定した場合() {
        var gs1 = GS1.ISBN978;
        assertEquals(3, gs1.length());
        assertEquals("978", gs1.toString());
        var charArray = gs1.toCharArray();

        assertEquals('9', charArray[0]);
        assertEquals('7', charArray[1]);
        assertEquals('8', charArray[2]);
    }

    @Test
    void ISBN979を指定した場合() {
        var gs1 = GS1.ISBN979;
        assertEquals(3, gs1.length());
        assertEquals("979", gs1.toString());

        var charArray = gs1.toCharArray();

        assertEquals('9', charArray[0]);
        assertEquals('7', charArray[1]);
        assertEquals('9', charArray[2]);
    }

}