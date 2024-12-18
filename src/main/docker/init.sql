create table public.author
(
    id         bigint not null
        primary key,
    first_name varchar(1024),
    last_name  varchar(1024)
);

alter table public.author
    owner to username;

create table public.publisher
(
    id   varchar(7) not null
        primary key,
    name varchar(1024)
);

alter table public.publisher
    owner to username;

create table public.book
(
    isbn13       varchar(22) not null
        primary key,
    price        integer,
    title        varchar(2048),
    publisher_id varchar(7)
        constraint uk19ss4s5ji828yqdpgm0otr93s
            unique
        constraint fkgtvt7p649s4x80y6f4842pnfq
            references public.publisher
);

alter table public.book
    owner to username;

create table public.book_author
(
    book_isbn13 varchar(22) not null
        constraint fky07kyotygqsra2mfms3unc02
            references public.book,
    author_id   bigint      not null
        constraint uk6cmg2roopa9a4c97uxetgf2e9
            unique
        constraint fkbjqhp85wjv8vpr0beygh6jsgo
            references public.author
);

alter table public.book_author
    owner to username;

INSERT INTO publisher
VALUES ('295', 'インプレス');
INSERT INTO author
VALUES ('1', '政純', '古賀');
INSERT INTO book
VALUES ('ISBN 978-4-295-00552-0', 3762,  'Docker実践ガイド 第2版 (impress top gear)','295');
INSERT INTO book_author
VALUES ( 'ISBN 978-4-295-00552-0', 1);
