/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.25 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_role` (
	`id` int (6),
	`name` varchar (240)
); 
insert into `t_role` (`id`, `name`) values('1','admin');
insert into `t_role` (`id`, `name`) values('2','user_manager');
insert into `t_role` (`id`, `name`) values('3','order_manager');
insert into `t_role` (`id`, `name`) values('4','user');
insert into `t_role` (`id`, `name`) values('5','addinfo_manager');
