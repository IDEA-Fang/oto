/*添加索引号 */
alter table biao add unique index(openid);

/*area表*/
drop  table if exists `tb_area`;

create table `tb_area`(
`area_id` int (10) not null auto_increment,
`area_name` varchar (32) not  null,
`area_desc` varchar (100) default null,
`priority` int (3) not  null default '0' comment '0还不知',
`create_time` timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
`update_time` timestamp  not  null default CURRENT_TIMESTAMP on UPDATE  current_timestamp comment '更新时间',
primary key (`area_id`),
unique key `UK_AREA` (`area_name`)
)engine=InnoDB auto_increment = 1 default charset= utf8;

drop table if exists `tb_person_info`;

create table `tb_person_info`(
`user_id` int (10) not null auto_increment,
`user_name` varchar (32) not null,
`brithday` datetime default null ,
`gender` tinyint(3) default null comment '0女-1男-2未知',
`phone` varchar (32) default null,
`email` varchar (128) default  null ,
`profile_img` varchar (1024)  default  null,
`customer_flag` int(2) NOT NULL DEFAULT '0',
`shop_owner_flag` int(2) NOT NULL DEFAULT '0',
`admin_flag` int(2) DEFAULT null,
`createTime` timestamp default current_timestamp ,
`updateTime` timestamp  default  current_timestamp on update current_timestamp ,
`enable_status` int (2) default '0' comment '0不允许进入，1可以进入',
primary key (`user_id`)
)engine=InnoDB auto_increment = 1 default charset= utf8 collate=utf8_unicode_ci;

drop table if exists `tb_wechat_auth`;

create table `tb_wechat_auth` (
  `wechat_auth_id` int(10) not null auto_increment,
  `user_id` int (10) not null ,
  `open_id` varchar (512) not null ,
  `createTime` timestamp default current_timestamp ,
  primary key (`wechat_auth_id`),
  KEY `fk_oauth_profile` (`user_id`),
  key `uk_oauth` (`open_id`(255)),
  constraint `fk_oauth_profile` foreign key  (`user_id`) references `tb_person_info` (`user_id`)
)engine = InnoDB auto_increment= 8 default charset =utf8 collate = utf8_unicode_ci;

drop table if exists `tb_local_auth`;

create table `tb_local_auth` (
  `local_auth_id` int(10) not null auto_increment,
  `user_id` int (10) not null ,
  `user_name` varchar (32) not null,
  `password` varchar(64) not null,

  `createTime` timestamp default current_timestamp ,
  `updateTime` timestamp default current_timestamp on update current_timestamp ,
  primary key (`local_auth_id`),
  unique key `uk_local_profile` (`user_name`),

  KEY `fk_local_profile` (`user_id`),
  constraint `fk_local_profile` foreign key  (`user_id`) references `tb_person_info` (`user_id`)
)engine = InnoDB auto_increment= 8 default charset =utf8 collate = utf8_unicode_ci;

/*headline表----------------------------*/
drop table if exists `tb_headline`;

create table `tb_headline` (
  `line_id` int(10) not null auto_increment,
  `line_name` varchar (32) default null,
  `line_link` varchar (1024) not null,
  `line_img` varchar(2048) NOT NULL,
  `priority` int (2) default '0',
  `enable_status` int(2) default '0',

  `createTime` timestamp default current_timestamp ,
  `updateTime` timestamp default current_timestamp on update current_timestamp ,

  primary key (`line_id`)
)engine = InnoDB auto_increment= 8 default charset =utf8 collate = utf8_unicode_ci;

/*tb_product表----------------------------*/
drop table if exists `tb_product`;

create table `tb_product`(
`product_id` int (10) not null auto_increment,
`product_name` varchar (32) not null,
`product_desc` varchar (64) default null,
`img_address` varchar (1024)  default  null,
`normal_price` varchar (32) default null,
`promotion_price` varchar (32) default null,
`priority` int (2) default '0',
`createTime` timestamp default current_timestamp ,
`updateTime` timestamp  default  current_timestamp on update current_timestamp ,
`enable_status` int (2) default '0' comment '0不允许进入，1可以进入',
`point` int (64) default null,
`product_category_id` int(10) NOT NULL,
`shop_id` int(10) NOT NULL,
primary key (`product_id`),

KEY `fk_product_shop` (`shop_id`),
KEY `fk_product_procate` (`product_category_id`),
constraint `fk_product_procate` foreign key (`product_category_id`) references `tb_product_category` (`product_category_id`),
constraint `fk_product_shop`  foreign key (`shop_id`) references `tb_shop` (`shop_id`)

)engine=InnoDB auto_increment = 1 default charset= utf8;



/*tb_product_category表----------------------------*/
drop table if exists `tb_product_category`;

create table `tb_product_category` (
  `product_category_id` int(10) not null auto_increment,
  `product_category_name` varchar (32) default null,
  `shop_id` int (10) not null default '0',
  `product_category_desc` varchar (64) default null,
  `priority` int (2) default '0',

  `createTime` timestamp default current_timestamp ,
  `updateTime` timestamp default current_timestamp on update current_timestamp ,
  primary key (`product_category_id`),

  KEY `fk_procate_shop` (`shop_id`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
)engine = InnoDB auto_increment= 16 default charset =utf8 ;


/*tb_product_img表----------------------------*/
drop table if exists `tb_product_img`;

create table `tb_product_img` (
  `product_img_id` int(10) not null auto_increment,
  `product_img_address` varchar (1024) not null,
  `product_img_desc` varchar (1024) default null,
  `priority` int (2) default '0',
  `createTime` timestamp default current_timestamp ,
   `product_id` int(20) DEFAULT NULL,
  primary key (`product_img_id`),

  KEY `fk_proimg_product` (`product_id`),
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) engine = InnoDB auto_increment= 16 default charset =utf8;



/*tb_shop表----------------------------*/
drop table if exists `tb_shop`;

create table `tb_shop`(
`shop_id` int (10) not null auto_increment,
`owner_id` int (10) not null ,
`shop_category_id` int (10) not null,
`shop_name` varchar (32) not null,
`shop_desc` varchar (64) default null,
`shop_address` varchar (1024)  default  null,
`shop_phone` varchar (32) default null,

`longitude` double(16,12) DEFAULT NULL,
`latitude` double(16,12) DEFAULT NULL,

`priority` int (2) default '0',
`createTime` timestamp default current_timestamp ,
`updateTime` timestamp  default  current_timestamp on update current_timestamp ,
`enable_status` int (2) default '0' comment '0不允许进入，1可以进入',
`advice` int (64) default null,

`point` int (64) default null,
`product_category_id` int(10) DEFAULT NULL,
primary key (`shop_id`)
,

KEY `fk_shop_profile` (`owner_id`),
KEY `fk_shop_area` (`area_id`),
KEY `fk_shop_shopcate` (`shop_category_id`),
KEY `fk_shop_parentcate` (`parent_category_id`),
constraint `fk_shop_profile` foreign key (`owner_id`) references `tb_person_info` (`user_id`),
constraint `fk_shop_area`  foreign key (`area_id`) references `tb_area` (`area_id`),
constraint `fk_shop_shopcate`  foreign key (`shop_category_id`) references `tb_shop_category` (`shop_category_id`),
constraint `fk_shop_parentcate`  foreign key (`parent_category_id`) references `tb_shop_category` (`shop_category_id`)
)engine=InnoDB auto_increment = 1 default charset= utf8 COLLATE=utf8_unicode_ci;


/*tb_shop_category表*/
drop table if exists `tb_shop_category`;

create table `tb_shop_category`(
`shop_category_id` int (10) not null auto_increment,
`shop_category_name` varchar (32) not null,
`shop_category_desc` varchar (64) default null,
`shop_category_img` varchar (2048)  default  null,
`priority` int (2) default '0',
`createTime` timestamp default current_timestamp ,
`updateTime` timestamp  default  current_timestamp on update current_timestamp ,
`parent_id` int(10) NOT NULL DEFAULT '0',
primary key (`shop_category_id`),

KEY `fk_shop_category_self` (`parent_id`),
constraint `fk_shop_category_self`  foreign key (`parent_id`) references `tb_shop_category` (`shop_category_id`)
)engine=InnoDB auto_increment = 1 default charset= utf8;







