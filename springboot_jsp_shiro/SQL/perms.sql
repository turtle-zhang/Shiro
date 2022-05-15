/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.25 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_perms` (
	`id` int (6),
	`name` varchar (320),
	`url` varchar (1020)
); 
insert into `t_perms` (`id`, `name`, `url`) values('1','user:*:*',NULL);
insert into `t_perms` (`id`, `name`, `url`) values('2','order:*:*',NULL);
insert into `t_perms` (`id`, `name`, `url`) values('3','user:add:*',NULL);
insert into `t_perms` (`id`, `name`, `url`) values('4','order:add:*',NULL);
