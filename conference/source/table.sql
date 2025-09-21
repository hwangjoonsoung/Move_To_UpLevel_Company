create table Registration
(
    id              int auto_increment
        primary key,
    good_name       varchar(50) null,
    price           int         null,
    amount          int         null,
    pay_method      varchar(10) null,
    trade_id    varchar(20)         null,
    order_id     varchar(20)         null,
    date_of_payment datetime    null,
    pay_status      tinyint(1)  null,
    name            varchar(30) null,
    affiliation     varchar(50) null,
    position        varchar(50) null,
    email           varchar(50) null,
    phone           varchar(30) null,
    registered      tinyint(1)  null,
    date_of_regist  datetime    null,
    date_of_create  datetime    null,
    member_id       int(20)         null
);

create table data_edit_log
(
    id              int auto_increment
        primary key,
    edit_table_id   int          null,
    edit_table_name varchar(10)  null,
    date_of_update  datetime     null,
    edit_by         varchar(100) null,
    edit_reason     varchar(255) null
);

create table fee
(
    id   int auto_increment
        primary key,
    name varchar(50) null,
    memo text        null
);

create table fee_item
(
    id     int auto_increment
        primary key,
    fee_id int         not null,
    price  int         null,
    member_type varchar(10) null,
    is_member tinyint(1)  null,
    constraint FK_fee_TO_fee_item_1
        foreign key (fee_id) references fee (id)
);

create table registrations_fee_item
(
    fee_item_id     int not null,
    registration_id int not null,
    primary key (fee_item_id, registration_id),
    constraint FK_Registration_TO_registrations_fee_item_1
        foreign key (registration_id) references Registration (id),
    constraint FK_fee_item_TO_registrations_fee_item_1
        foreign key (fee_item_id) references fee_item (id)
);


