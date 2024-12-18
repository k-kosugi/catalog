package com.redhat.catalog.domain_model.book.isbn13;

/**
 * Separator Enum.
 *
 * @author kkosugi
 */
public enum Separator {
    HYPHEN("-"), SPACE(" ");

    private final String separator;

    /**
     * コンストラクタ.
     *
     * @param separator ハイフンかスペースを指定する.
     */
    Separator(String separator) {
        this.separator = separator;
    }

    @Override
    public String toString() {
        return this.separator;
    }
}
