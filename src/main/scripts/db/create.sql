use apppath;

drop table `tb_point`;
create table if not exists `tb_point` (
    `id` bigint(20) primary key auto_increment,
    `uid` bigint(20) DEFAULT NULL,
    `udid` varchar(128) not null,
    `package_name` varchar(128) not null,
    `start` timestamp not null, 
    `duration` bigint(20) not null, 
    `data` text default null,
    `longitude` smallint DEFAULT NULL,
    `latitude` smallint DEFAULT NULL,
    index `idx_udid_start` (`udid`, `start`),
    index `idx_udid_duration` (`udid`, `duration`)
) engine=InnoDB default charset=utf8;
