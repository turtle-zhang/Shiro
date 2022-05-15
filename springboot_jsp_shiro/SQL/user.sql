/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.25 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_user` (
	`id` int (6),
	`username` varchar (160),
	`password` varchar (160),
	`salt` varchar (1020)
); 
insert into `t_user` (`id`, `username`, `password`, `salt`) values('1','admin','f97c3656244080f98896bf262e73c450','Y6c!RI*q');
insert into `t_user` (`id`, `username`, `password`, `salt`) values('2','zhangsan','f97c3656244080f98896bf262e73c450','Y6c!RI*q');
insert into `t_user` (`id`, `username`, `password`, `salt`) values('3','usermanager','f97c3656244080f98896bf262e73c450','Y6c!RI*q');
insert into `t_user` (`id`, `username`, `password`, `salt`) values('4','ordermanager','f97c3656244080f98896bf262e73c450','Y6c!RI*q');
insert into `t_user` (`id`, `username`, `password`, `salt`) values('5','powermanager','f97c3656244080f98896bf262e73c450','Y6c!RI*q');
insert into `t_user` (`id`, `username`, `password`, `salt`) values('6','addinfomanager','f97c3656244080f98896bf262e73c450','Y6c!RI*q');
