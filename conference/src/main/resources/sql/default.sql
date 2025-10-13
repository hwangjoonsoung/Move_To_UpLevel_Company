SET FOREIGN_KEY_CHECKS = 0;
drop table if exists data_edit_logs;
drop table if exists fees;
drop table if exists fee_items;
drop table if exists registrations;
drop table if exists booths;
drop table if exists staffs;
SET FOREIGN_KEY_CHECKS = 1;

create table data_edit_logs
(
    id              int auto_increment
        primary key,
    edit_table_id   int          null,
    edit_table_name varchar(10)  null,
    date_of_update  datetime     null,
    edit_by         varchar(100) null,
    edit_reason     varchar(255) null
);

create table fees
(
    id   int auto_increment
        primary key,
    name varchar(50) null,
    description text        null
);

create table fee_items
(
    id     int auto_increment
        primary key,
    fee_id int         not null,
    price  int         null,
    member_type varchar(10) null,
    is_member tinyint(1)  null,
    constraint FK_fees_TO_fee_item_1
        foreign key (fee_id) references fees (id)
);

create table registrations
(
    id              int auto_increment
        primary key,
    good_name       varchar(50) null,
    fee_item_id       int null,
    price           int         null,
    amount          int         null,
    pay_method      varchar(10) null,
    trade_id    varchar(20)         null,
    order_id     varchar(20)         null,
    date_of_payment datetime    null,
    pay_status      tinyint(1)  null,
    name            varchar(30) null,
    birth           date null,
    affiliation     varchar(50) null,
    position        varchar(50) null,
    email           varchar(50) null,
    phone           varchar(30) null,
    registered      tinyint(1)  null,
    date_of_regist  datetime    null,
    date_of_create  datetime    null,
    is_deleted  tinyint(1)    default 0,
    member_id       int(10)         null,
    constraint FK_fee_item_TO_registrations_1
        foreign key (fee_item_id) references fee_items (id)
);

CREATE TABLE booths
(
    id                   int NOT NULL primary key auto_increment,
    fee_item_id          int NOT NULL,
    company_name         varchar(50) NULL,
    ceo_name             varchar(50) NULL,
    company_phone_number varchar(30) NULL,
    booth_count          int(1)	NULL,
    booth_ids            varchar(20) NULL,
    manager_name         varchar(30) NULL,
    manager_affiliations varchar(50)	NULL,
    manager_phone_number varchar(30) NULL,
    manager_email        varchar(50) NULL,
    password             varchar(50)	NULL,
    price                int(10)	NULL,
    payment_status       tinyint(1)	NULL,
    amount               int(10)	NULL,
    payment_method       varchar(10) NULL,
    date_of_payment      datetime NULL,
    date_of_create      datetime NULL,
    date_of_update      datetime NULL,
    constraint FK_fee_item_TO_booths_1
        foreign key (fee_item_id) references fee_items (id)
);

CREATE TABLE staffs
(
    id          int NOT NULL,
    booth_id    int NOT NULL,
    name        varchar(30) NULL,
    affiliation varchar(50) NULL,
    position    varchar(50) NULL,
    constraint FK_booth_TO_staff_1
        foreign key (booth_id) references booths (id)
);

insert into fees (name, description)  values ('default', 'using test');
insert into fee_items (is_member , price , fee_id , member_type ) values (0,1000,1,0)
