CREATE DATABASE DAStore;

use DAStore;

create table category
(
	id varchar(40) primary key,
	name varchar(40) not null unique,
	description varchar(255)
);

create table goods
(
	id varchar(40) primary key,
	name varchar(40) not null unique,
	description varchar(255),
	price decimal(8,2) not null,
	category_id varchar(40),
	constraint category_id_fk foreign key(category_id) references category(id)
);

create table user
(
	id varchar(40) primary key,
	name varchar(40) not null unique,
	password varchar(40) not null,
	email varchar(40) not null,
	address varchar(255) not null
);

create table orders
(
	id varchar(40) primary key,
	time datetime not null,
	status boolean not null,
	total_price decimal(8,2) not null,
	user_id varchar(40),
	constraint user_id_fk foreign key(user_id) references user(id)
);

create table orderitem
(
	id varchar(40) primary key,
	goods_id varchar(40),
	number int not null,
	total_price decimal(8,2) not null,
	order_id varchar(40),
	constraint goods_id_fk foreign key(goods_id) references goods(id),
	constraint order_id_fk foreign key(order_id) references orders(id)
);

create table manager
(
    id varchar(40) primary key,
    username varchar(40) not null unique,
    constraint managerid_fk foreign key(id) references user(id)
);

create table logs
(
    id varchar(40) primary key,
    userid varchar(40) not null,
    goodsid varchar(40) not null,
    num int not null,
    constraint goodsid_fk foreign key(goodsid) references goods(id),
    constraint userid_fk foreign key(userid) references user(id)
);

INSERT INTO user(id, name, password, email, address) VALUES("0", "root", "haha2333", "root@email.com", "ROOTADDRESS");
INSERT INTO manager(id, username) VALUES("0", "root");