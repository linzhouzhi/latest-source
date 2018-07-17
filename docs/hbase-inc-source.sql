create database if not exists hbase_inc_source;
use hbase_inc_source;

create table if not exists meta_info(
    id int auto_increment primary key,
    inc_object_name varchar(100) not null,
    table_name varchar(255) not null,
    family_column varchar(500) not null,
    zk_address varchar(255) not null,
    kafka_address varchar(255) not null,
    kfaka_topic varchar(255) not null,
    max_update_range_time tinyint not null,
    update_range_time tinyint not null,
    start_switch tinyint,
    update_time bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8;