package com.redhat.catalog.infrastructure;

import com.redhat.catalog.infrastructure.dao.BookDAO;
import org.springframework.data.repository.CrudRepository;

public interface BookPostgresql extends CrudRepository<BookDAO, String> {
}
