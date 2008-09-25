CREATE TABLE `clients` (
  `id` int(11) NOT NULL auto_increment,
  `firstName` varchar(80) default NULL,
  `lastName` varchar(80) default NULL,
  `dateOfBirth` datetime default NULL,
  PRIMARY KEY  (`id`)
);