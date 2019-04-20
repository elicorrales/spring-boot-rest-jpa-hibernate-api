drop table if exists `order`;

drop table if exists `orders`;

drop table if exists `customer`;
create table `customer` (
	`id`			int(11) not null auto_increment,
	`fname`			varchar(50) not null,
    `lname`			varchar(50) not null,
    `email`			varchar(100) not null,
    primary key (`id`),
    unique key (`email`),
    constraint unique_fname_lname unique (`fname`,`lname`)
) engine=InnoDB auto_increment=1 default charset=latin1;

create table `orders` (
	`id`			int(11) not null auto_increment,
	`number`		varchar(20) not null,
    `description`	varchar(100) not null,
    `date_ordered`	varchar(50) not null,
    `status`		varchar(50) not null,
    `customer_id`	int(11),
    primary key (`id`),
    unique key (`number`),
    constraint `fk_customer` foreign key (`customer_id`) references `customer` (`id`)
) engine=InnoDB auto_increment=1 default charset=latin1;
