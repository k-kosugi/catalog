package com.redhat.catalog.domain_model.book.isbn13;

/**
 * GS1 enum.<br/>
 * ISBNの最初の要素は、GS1（旧EANインターナショナル）が利用可能にした3桁の番号である。<br/>
 * すでにGS1によって利用可能になっている接頭辞は978と979だが、ISBNシステムの継続的な容量を確保するために必要に応じて、
 * 将来さらに接頭辞の割り当てが行われる可能性がある。<br/><br/>
 * <em>EXAMPLE: 978</em><br/>
 *
 * @author kkosugi
 */
public enum GS1 {

    ISBN978("978"), ISBN979("979");

    public static final int LENGTH = 3;

    private final String value;

    /**
     * コンストラクタ.<br/>
     *
     * @param value GS1 を示す 978 か 979 の値.
     */
    GS1(final String value) {
        this.value = value;
    }

    /**
     * 長さ
     *
     * @return GS1 の長さ
     */
    public int length() {
        return this.value.length();
    }

    /**
     * char の配列を返却
     *
     * @return char[]
     */
    public char[] toCharArray() {
        return this.value.toCharArray();
    }

    @Override
    public String toString() {
        return this.value;
    }

}
