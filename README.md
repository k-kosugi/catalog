# 使い方

## 事前にインストールが必要なもの
* OpenJDK のインストール
* Podman のインストール

※ Maven Wrapper コマンドを利用するためにインターネットに接続してある必要があります。

## プロジェクトのダウンロード
GitHub のプロジェクトのダウンロードからダウンロードするか、Git がインストール済みの場合、以下のコマンドを実行してダウンロードする。

```shell
git clone 
```

### PostgreSQL コンテナの実行
1. `src/main/docker` ディレクトリに移動
    ```shell
    cd src/main/docker
    ```
1. `podman build` コマンドを使用して PostgreSQL のコンテナイメージを作成
    ```shell
    podman build -t catalog-db:v1 -f ./Dockerfile.postgresql
    ```
1. コンテナイメージを元に PostgreSQL を起動
    ```shell
    podman run -d --name catalog-db -p 5432:5432 catalog-db:v1
    ```
1. 起動した PostgreSQL コンテナにログイン
    ```shell
    podman exec -it catalog-db /bin/bash
    ```
1. PosgreSQL のコマンドを実行
    ```shell
    psql -U username catalog-db 
    ```
1. Book テーブルの参照
    ```shell
    select * from book;
    ```

上記の結果、以下のテーブルが表示されれば問題なし。
```
psql (17.2 (Debian 17.2-1.pgdg120+1))
Type "help" for help.

catalog-db=# select * from book;
         isbn13         | price |                   title                   | publisher_id 
------------------------+-------+-------------------------------------------+--------------
 ISBN 978-4-295-00552-0 |  3762 | Docker実践ガイド 第2版 (impress top gear) | 295
(1 row)

```

1. ctrl + c を入力して PostgreSQL から抜ける
2. ctrl + c を入力してコンテナから抜ける

