/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.7.22 : Database - mregister
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mregister` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mregister`;

/*Table structure for table `agents` */

DROP TABLE IF EXISTS `agents`;

CREATE TABLE `agents` (
  `agent_no` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `device_type` varchar(255) DEFAULT NULL,
  `id_attachment` varchar(255) DEFAULT NULL,
  `id_no` bigint(20) DEFAULT NULL,
  `imei_no` bigint(20) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `mobile` bigint(20) DEFAULT NULL,
  `sales_area_id` bigint(20) DEFAULT NULL,
  `sales_region_id` bigint(20) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `signed_contact` bigint(20) DEFAULT NULL,
  `signed_name` varchar(255) DEFAULT NULL,
  `tdr_id` bigint(20) DEFAULT NULL,
  `town` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`agent_no`),
  KEY `created_by` (`created_by`),
  KEY `updated_by` (`updated_by`),
  KEY `sales_area_id` (`sales_area_id`),
  KEY `sales_region_id` (`sales_region_id`),
  CONSTRAINT `agents_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`),
  CONSTRAINT `agents_ibfk_2` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`),
  CONSTRAINT `agents_ibfk_3` FOREIGN KEY (`sales_area_id`) REFERENCES `sales_areas` (`id`),
  CONSTRAINT `agents_ibfk_4` FOREIGN KEY (`sales_region_id`) REFERENCES `sales_regions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `agents` */

insert  into `agents`(`agent_no`,`created_at`,`updated_at`,`created_by`,`updated_by`,`address`,`device_type`,`id_attachment`,`id_no`,`imei_no`,`location`,`mobile`,`sales_area_id`,`sales_region_id`,`signature`,`signed_contact`,`signed_name`,`tdr_id`,`town`) values 
(123456,'2018-09-30 05:45:32','2018-09-30 05:45:32',1,1,'Test address','Phone','attt',45366763,456789876543567,'Nairobi',723443434,5,5,'vkfkdf',723424343,'Sandra Karanja',4,'Nairobi'),
(133333,'2018-10-10 14:04:48','2018-10-10 14:04:48',1,1,'Test address','Phone','attt',11155888,123333333333,'Nyamira',711222555,1,4,'vkfkdf',788699999,'Teeer',4,'Nyamira'),
(243534,'2018-09-30 05:48:11','2018-09-30 05:48:11',1,1,'Test address','Phone','attt',32453453,324534563466,'Kilifi',711401187,7,7,'vkfkdf',733446677,'Clifford Masi',4,'Kilifi'),
(344354,'2018-09-30 05:59:42','2018-09-30 05:59:42',6,6,'Test address','Tablet','attt',56575757,324246523523525,'Kakamega',7112323424,1,1,'vkfkdf',7343424234,'Kibet',4,'Kakamega'),
(444588,'2018-10-10 14:07:41','2018-10-10 14:07:41',1,1,'Test address','Phone','attt',45588899,11555555555,'Ybvvvcv',999888888,2,6,'vkfkdf',3685555888,'Gbbbnnk',4,'Ybvvvcv');

/*Table structure for table `asms` */

DROP TABLE IF EXISTS `asms`;

CREATE TABLE `asms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `id_no` bigint(20) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `sales_region_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `sales_region_id` (`sales_region_id`),
  CONSTRAINT `asms_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `asms_ibfk_2` FOREIGN KEY (`sales_region_id`) REFERENCES `sales_regions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `asms` */

insert  into `asms`(`id`,`created_at`,`updated_at`,`first_name`,`id_no`,`last_name`,`sales_region_id`,`user_id`) values 
(1,'2018-09-29 18:08:08','2018-09-29 18:08:08','clifford',2498888,'masi',1,4);

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

insert  into `roles`(`id`,`name`) values 
(1,'ROLE_ADMIN'),
(3,'ROLE_ASM'),
(4,'ROLE_DISTRIBUTOR'),
(2,'ROLE_TDR');

/*Table structure for table `sales_areas` */

DROP TABLE IF EXISTS `sales_areas`;

CREATE TABLE `sales_areas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `sales_region_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sales_region_id` (`sales_region_id`),
  CONSTRAINT `sales_areas_ibfk_1` FOREIGN KEY (`sales_region_id`) REFERENCES `sales_regions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `sales_areas` */

insert  into `sales_areas`(`id`,`created_at`,`updated_at`,`name`,`sales_region_id`) values 
(1,'2018-09-29 17:30:08','2018-09-29 17:30:08','KAKAMEGA',4),
(2,'2018-09-29 17:30:49','2018-09-29 17:30:49','N. UPPER',6),
(3,'2018-09-29 17:31:20','2018-09-29 17:31:20','WUNDANYI',7),
(4,'2018-09-29 17:31:40','2018-09-29 17:31:40','KITUI',8),
(5,'2018-09-29 17:32:02','2018-09-29 17:32:02','SOUTH COAST',9),
(6,'2018-09-29 17:32:23','2018-09-29 17:32:23','NANYUKI TIMAU',10),
(7,'2018-09-29 17:32:41','2018-09-29 17:32:41','NORTH COAST',9);

/*Table structure for table `sales_regions` */

DROP TABLE IF EXISTS `sales_regions`;

CREATE TABLE `sales_regions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `sales_regions` */

insert  into `sales_regions`(`id`,`created_at`,`updated_at`,`name`) values 
(1,'2018-09-29 17:21:51','2018-09-29 17:21:51','Nairobi Area'),
(2,'2018-09-29 17:22:04','2018-09-29 17:22:04','Mombasa Area'),
(3,'2018-09-29 17:22:17','2018-09-29 17:22:17','Rift Valley Area'),
(4,'2018-09-29 17:25:56','2018-09-29 17:25:56','WESTERN NYANZA'),
(5,'2018-09-29 17:26:16','2018-09-29 17:26:16','GREATER WESTERN'),
(6,'2018-09-29 17:26:32','2018-09-29 17:26:32','N. WEST'),
(7,'2018-09-29 17:26:49','2018-09-29 17:26:49','TSAVO'),
(8,'2018-09-29 17:27:12','2018-09-29 17:27:12','N. EAST'),
(9,'2018-09-29 17:27:26','2018-09-29 17:27:26','COAST'),
(10,'2018-09-29 17:27:43','2018-09-29 17:27:43','LAIKIPIA');

/*Table structure for table `tdrs` */

DROP TABLE IF EXISTS `tdrs`;

CREATE TABLE `tdrs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `asm_id` bigint(20) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `id_no` bigint(20) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `sales_area_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `asm_id` (`asm_id`),
  KEY `sales_area_id` (`sales_area_id`),
  CONSTRAINT `tdrs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `tdrs_ibfk_2` FOREIGN KEY (`asm_id`) REFERENCES `asms` (`id`),
  CONSTRAINT `tdrs_ibfk_3` FOREIGN KEY (`sales_area_id`) REFERENCES `sales_areas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tdrs` */

insert  into `tdrs`(`id`,`created_at`,`updated_at`,`asm_id`,`first_name`,`id_no`,`last_name`,`sales_area_id`,`user_id`) values 
(1,'2018-09-29 18:15:46','2018-09-29 18:15:46',1,'Maina',44994499,'James',1,6);

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_id`,`role_id`) values 
(1,1),
(6,2),
(4,3);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`id`,`created_at`,`updated_at`,`email`,`name`,`password`,`username`) values 
(1,'2018-09-29 19:48:42','2018-09-29 19:48:39','admin@mregister.com','admin','$2a$09$KBmnu.ubVuVn/FmrRRAtgu8l2BeecA1ZD6rQ30kOSV6gBvqZQud72','admin'),
(4,'2018-09-29 18:08:07','2018-09-29 18:08:07','cmasi@safaricom.co.ke','Clifford Masi','$2a$10$ScFWyExcyHlL9eDQUpsk3.T7T1cDx429lsWU3BP0EHjdpSGx1bjwq','cmasi'),
(6,'2018-09-29 18:15:46','2018-09-29 18:15:46','jmaina@safaricom.co.ke','James Maina','$2a$10$0kxbTm16n0RYeEVBxQXP8OK./VsLUAGBV2xG2NA9kWS9pUxP1hzBO','jmaina');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
