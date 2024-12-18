package com.redhat.catalog.domain_model.book.isbn13;

import java.util.Objects;

/**
 * ISBN レコード.<br/>
 * 2007年1月1日以降、各国のISBN登録機関は、以下の要素からなる13桁のISBNのみを提供している：
 * <ul>
 * <li>GS1</li>
 * <li>Registration group</li>
 * <li>Registrant</li>
 * <li>Publication</li>
 * <li>Check digit</li>
 * </ul>
 * 印刷時には、ISBNの前に必ず "ISBN"の文字が付きます。<br/>
 * 注：ラテンアルファベットが使用されていない国では、ラテン文字 "ISBN"に加えて、現地の文字による省略形が使用されることがある。<br/>
 * ISBNは5つの要素に分けられ、そのうち3つは可変長であり、最初と最後の要素は固定長である。<br/>
 * 最初と最後の要素は固定長である。可読形式で表示する場合、<strong>各要素はハイフンまたはスペースで明確に区切らなければならない</strong>：<br/><br/>
 * ISBN 978-92-95055-12-4<br/>
 * または<br/>
 * ISBN 978 92 95055 12 4<br/>
 *
 * @author kkosugi
 */
public class ISBN13 {

    public static final String PREFIX = "ISBN ";

    public static final int MAX_LENGTH_WITHOUT_PREFIX = 13;

    public static final int COUNT_OF_ELEMENT = 5;

    public static final int MAX_LENGTH_WITH_SEPARATOR = MAX_LENGTH_WITHOUT_PREFIX + COUNT_OF_ELEMENT - 1;

    public static final int MAX_LENGTH = MAX_LENGTH_WITH_SEPARATOR + 5;

    public static final Separator SEPARATOR = Separator.HYPHEN;

    private final GS1 gs1;

    private final RegistrationGroup registrationGroup;

    private final Registrant registrant;

    private final Publication publication;

    /**
     * コンストラクタ.
     *
     * @param gs1               GS1 オブジェクト. 978 か 979 のどちらかを保持する.
     * @param registrationGroup RegistrationGroup オブジェクト. 日本の場合 4 を保持する.
     * @param registrant        Registrant オブジェクト. 出版社を特定可能なコードを保持する.
     * @param publication       Publication オブジェクト. 書籍を一意に特定できるコードを保持する.
     */
    public ISBN13(GS1 gs1, RegistrationGroup registrationGroup, Registrant registrant, Publication publication) {
        // null チェック
        if (Objects.isNull(gs1)) {
            throw new IllegalArgumentException("gs1 には null を設定できません.");
        }

        if (Objects.isNull(registrationGroup)) {
            throw new IllegalArgumentException("registrationGroup には null を設定できません.");
        }

        if (Objects.isNull(registrant)) {
            throw new IllegalArgumentException("registrant には null を設定できません.");
        }

        if (Objects.isNull(publication)) {
            throw new IllegalArgumentException("publication には null を設定できません.");
        }

        // 長さチェック
        if (gs1.length() + registrationGroup.length() + registrant.length() + publication.length() != (MAX_LENGTH_WITHOUT_PREFIX - 1)) {
            throw new IllegalArgumentException("ISBN13 を構成する各要素の長さの合計はチェックデジットを除いて " + (MAX_LENGTH_WITHOUT_PREFIX - 1) + " になる必要があります.");
        }

        this.gs1 = gs1;
        this.registrationGroup = registrationGroup;
        this.registrant = registrant;
        this.publication = publication;
    }

    /**
     * String の文字列から ISBN13 インスタンスを生成する
     *
     * @param strIsbn13 String 型で保持された ISBN13.
     */
    public ISBN13(String strIsbn13) {
        // null チェック
        if (Objects.isNull(strIsbn13)) {
            throw new IllegalArgumentException("strIsbn13 には null を設定することはできません.");
        }

        // 文字列長が24以外
        if (strIsbn13.length() != MAX_LENGTH) {
            throw new IllegalArgumentException("strIsbn13 の文字列長は " + MAX_LENGTH_WITH_SEPARATOR + " である必要があります.");
        }

        // 'ISBN 'で分離
        var strIsbn13SplitedByPrefix = strIsbn13.split(PREFIX.toString());
        if (strIsbn13SplitedByPrefix.length != 2) {
            throw new IllegalArgumentException("strIsbn13 は '" + PREFIX + "' で始まる必要があります.");
        }

        // Separator で分離
        var strIsbn13SplitedBySpace = strIsbn13SplitedByPrefix[1].split(SEPARATOR.toString());

        // 分離した要素の長さが MAX_COUNT を満たさない場合
        if (strIsbn13SplitedBySpace.length != COUNT_OF_ELEMENT) {
            throw new IllegalArgumentException("strIsbn13 は '" + SEPARATOR.toString() + "' で分離した要素の数が " + COUNT_OF_ELEMENT + " である必要があります.");
        }

        // ISBN13 オブジェクトの生成
        this.gs1 = GS1.ISBN978.toString().startsWith(strIsbn13SplitedBySpace[0]) ? GS1.ISBN978 : GS1.ISBN979;
        this.registrationGroup = new RegistrationGroup(strIsbn13SplitedBySpace[1]);
        this.registrant = new Registrant(strIsbn13SplitedBySpace[2]);
        this.publication = new Publication(strIsbn13SplitedBySpace[3]);

        // チェックデジットの確認
        var checkDigit = Integer.parseInt(strIsbn13SplitedBySpace[4]);
        if (this.checkDigit() != checkDigit) {
            throw new IllegalArgumentException("チェックデジットの値が一致しません.");
        }
    }

    /**
     * GS1 を取得
     *
     * @return GS1 インスタンス
     */
    public GS1 getGs1() {
        return this.gs1;
    }

    /**
     * Registration Group を取得
     *
     * @return RegistrationGroup インスタンス
     */
    public RegistrationGroup getRegistrationGroup() {
        return this.registrationGroup;
    }

    /**
     * Registrant の取得
     *
     * @return Registrant インスタンス
     */
    public Registrant getRegistrant() {
        return this.registrant;
    }

    /**
     * Publication の取得
     *
     * @return Publication インスタンス
     */
    public Publication getPublication() {
        return this.publication;
    }

    /**
     * <strong>978-4-87311-758</strong> の場合、チェックデジットは以下のように計算される.
     * <ol>
     * <li>奇数桁 : 9, 8, 8, 3, 1, 5 : そのまま加算する(9 + 8 + 8 + 3 + 1 + 5 = 34)</li>
     * <li>偶数桁 : 7, 4, 7, 1, 7, 8 : 3倍して加算する((7 + 4 + 7 + 1 + 7 + 8) * 3 = 34 * 3 = 132)</li>
     * <li>上記の加算 : 34 + 132 = 136</li>
     * <li>3 の値を 10 で割った余りを 10 から減算 : 10 - (136 % 10) = 10 - 6 = 4</li>
     * <li>チェックデジットは 4</li>
     * </ol>
     *
     * @return Check Digit.
     */
    public int checkDigit() {
        int returnCode = 0;
        var isbnCharArray = new char[MAX_LENGTH_WITHOUT_PREFIX - 1];

        var gs1CharArray = this.gs1.toCharArray();
        var registrationGroupCharArray = this.registrationGroup.value().toCharArray();
        var registrantCharArray = this.registrant.value().toCharArray();
        var publicationCharArray = this.publication.value().toCharArray();

        // char[12] に gs1 - publication までコピー
        System.arraycopy(gs1CharArray, 0, isbnCharArray, 0, gs1CharArray.length);
        System.arraycopy(registrationGroupCharArray, 0, isbnCharArray, gs1CharArray.length, registrationGroupCharArray.length);
        System.arraycopy(registrantCharArray, 0, isbnCharArray, gs1CharArray.length + registrationGroupCharArray.length, registrantCharArray.length);
        System.arraycopy(publicationCharArray, 0, isbnCharArray, gs1CharArray.length + registrationGroupCharArray.length + registrantCharArray.length, publicationCharArray.length);

        // 奇数の場合はそのまま、偶数の場合は3倍して加える
        for (int i = 0; i < isbnCharArray.length; i++) {
            var intChar = Integer.parseInt(String.valueOf(isbnCharArray[i]));
            returnCode += ((i + 1) % 2 == 0) ? 3 * intChar : intChar;
        }

        // 10 で割った余りを代入
        returnCode = (returnCode % 10 == 0) ? 0 : 10 - (returnCode % 10);

        return returnCode;
    }

    @Override
    public String toString() {
        return PREFIX + this.gs1.toString() + SEPARATOR
                + this.registrationGroup.value() + SEPARATOR
                + this.registrant.value() + SEPARATOR
                + this.publication.value() + SEPARATOR
                + this.checkDigit();
    }
}
