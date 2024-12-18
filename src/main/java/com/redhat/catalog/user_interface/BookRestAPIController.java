package com.redhat.catalog.user_interface;

import com.redhat.catalog.application_service.BookApplicationService;
import com.redhat.catalog.application_service.Converter;
import com.redhat.catalog.domain_model.book.Book;
import com.redhat.catalog.domain_model.book.isbn13.ISBN13;
import com.redhat.catalog.infrastructure.dao.BookDAO;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookRestAPIController {

    private BookApplicationService bookApplicationService;

    @Autowired
    public BookRestAPIController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @RequestMapping(value = "/find/", method = RequestMethod.GET)
    public BookDAO findByIsbn13(@PathParam("isbn13") String strIsbn13) {
        // null チェック
        if (Objects.isNull(strIsbn13)) {
            throw new IllegalArgumentException("isbn13 には null を設定することはできません.");
        }

        // 空文字チェック
        if (strIsbn13.isEmpty()) {
            throw new IllegalArgumentException("isbn13 には空文字を設定することはできません.");
        }

        // String 型を ISBN13 型に変換
        var isbn13 = new ISBN13(strIsbn13);

        // 検索
        Book book = bookApplicationService.findByIsbn13(isbn13);
        if (book == null) {
            // null の場合そのまま返却
            return null;
        }

        // 本来は JSON 専用の Model に変換して返却するべきだが、ここではデータベース用のモデルを使用する
        return Converter.convertToBookDaoFromBook(book);
    }

    @RequestMapping("/all")
    public Iterable<BookDAO> findAll() {
        return Converter.convertToBookDaoListFromBookList(this.bookApplicationService.findAll());
    }

}
