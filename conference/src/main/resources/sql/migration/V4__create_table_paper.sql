CREATE TABLE category
(
    id             int primary key auto_increment NOT NULL,
    paper_type     varchar(20) NULL,
    category_name  varchar(100) NULL,
    deleted        boolean NULL,
    date_of_create datetime NULL,
    date_of_update datetime NULL
);

CREATE TABLE paper
(
    Id                int primary key auto_increment NOT NULL,
    category_id       int NOT NULL,
    title             varchar(100) NULL,
    presentation_type varchar(40) NULL,
    abstract_language varchar(10) NULL,
    keyword           varchar(100) NULL,
    abstract          text NULL,
    date_of_create    datetime NULL,
    date_of_update    datetime NULL,
    deleted           boolean NULL,

    constraint FK_category_TO_paper_1
        foreign key (category_id) references category (id)
);

CREATE TABLE author_affiliation
(
    id               int primary key auto_increment NOT NULL,
    paper_id         int NOT NULL,
    affiliation_code int NULL,
    affiliation      varchar(100) NULL,
    deleted          boolean NULL,

    constraint FK_author_affiliation_to_paper_1
    foreign key (paper_id) references paper(id)
);

CREATE TABLE author
(
    id               int primary key auto_increment NOT NULL,
    paper_id         int NOT NULL,
    affiliation_code int NULL,
    deleted          boolean NULL,

    constraint FK_author_to_paper_1
        foreign key (paper_id) references paper(id)
);
