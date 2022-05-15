/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.25 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_user_role` (
	`id` int (6),
	`userid` int (6),
	`roleid` int (6)
); 
insert into `t_user_role` (`id`, `userid`, `roleid`) values('1','1','1');
insert into `t_user_role` (`id`, `userid`, `roleid`) values('2','2','4');
insert into `t_user_role` (`id`, `userid`, `roleid`) values('3','3','2');
insert into `t_user_role` (`id`, `userid`, `roleid`) values('4','4','3');
insert into `t_user_role` (`id`, `userid`, `roleid`) values('5','6','5');
