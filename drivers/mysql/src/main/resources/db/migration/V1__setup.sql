create table customer(
    id bigint auto_increment not null primary key,
    uuid varchar(36) not null unique,
    document varchar(14) unique,
    name varchar(200) not null,
    email varchar(255) not null,
    enabled boolean not null,
    created datetime not null,
    last_updated datetime not null,
    version integer not null
);

create table product(
    id bigint auto_increment not null primary key,
    uuid varchar(36) not null unique,
    name varchar(200) not null,
    description text,
    category enum('SANDWICH', 'BEVERAGE', 'DESSERT', 'SIDE_DISH') not null,
    price numeric(12,2) not null,
    created datetime not null,
    last_updated datetime not null,
    version integer not null
);

create table purchase(
    id bigint auto_increment not null primary key,
    uuid varchar(36) not null unique,
    code varchar(4) not null,
    external_id varchar(60) not null unique,
    customer_id bigint,
    status enum('CREATED', 'WAITING_PAYMENT', 'PAID_SUCCESS', 'PAID_ERROR', 'WAITING_MAKE', 'MAKING', 'MADE', 'DELIVERED') not null default 'CREATED',
    date date not null default (curdate()),
    created datetime not null,
    last_updated datetime not null,
    version integer not null,
    constraint fk_customer_id foreign key (customer_id) references customer(id)
);

create table purchase_item(
    purchase_id bigint not null,
    product_id bigint not null,
    sequence bigint not null,
    quantity integer not null,
    price numeric(12,2),
    discount numeric(5,2),
    created datetime not null,
    last_updated datetime not null,
    version integer not null,
    constraint fk_product_id foreign key (product_id) references product(id),
    constraint fk_purchase_id foreign key (purchase_id) references  purchase(id),
    constraint pk_purchase_item primary key (purchase_id, product_id, sequence)
);

create table payment(
    purchase_id bigint not null primary key,
    uuid varchar(36) not null unique,
    payment_id varchar(100) not null,
    payment_url text not null,
    status enum('CREATED', 'PAID', 'ERROR') not null,
    created datetime not null,
    last_updated datetime not null,
    version integer not null,
    constraint fk_payment_purchase_id foreign key (purchase_id) references purchase(id)
);