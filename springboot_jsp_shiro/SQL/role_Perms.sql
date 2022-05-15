/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.25 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_role_perms` (
	`id` int (6),
	`roleid` int (6),
	`permsid` int (6)
); 
insert into `t_role_perms` (`id`, `roleid`, `permsid`) values('1','1','1');
insert into `t_role_perms` (`id`, `roleid`, `permsid`) values('2','1','2');
insert into `t_role_perms` (`id`, `roleid`, `permsid`) values('3','2','1');
insert into `t_role_perms` (`id`, `roleid`, `permsid`) values('4','3','2');
insert into `t_role_perms` (`id`, `roleid`, `permsid`) values('5','5','3');
insert into `t_role_perms` (`id`, `roleid`, `permsid`) values('6','5','4');
