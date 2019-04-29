USE dbSalesCenter;

#FIRST USER
INSERT INTO tb_userGroup VALUES (1, '2018-01-01', '2018-01-01', 'Master', 1);
INSERT INTO tb_permission VALUES (1, TRUE, TRUE, TRUE, TRUE, TRUE, 1, TRUE, 1);
INSERT INTO tb_permission VALUES (2, TRUE, TRUE, TRUE, TRUE, TRUE, 2, TRUE, 1);
INSERT INTO tb_permission VALUES (3, TRUE, TRUE, TRUE, TRUE, TRUE, 3, TRUE, 1);
INSERT INTO tb_permission VALUES (4, TRUE, TRUE, TRUE, TRUE, TRUE, 4, TRUE, 1);
INSERT INTO tb_user VALUES (1, '898767', 'en', 'admin', 'admin',1 ,1 ,'rocket', 1);
INSERT INTO tb_person VALUES (1, '2018-01-01', '2018-01-01', '1979-08-01', '08650094066', 'admin@admin.com', 1, 'admin', 'admin', '457651234', 1, 1);

#GENERAL PERSON QUERY
SELECT 
	* 
FROM 
	tb_person AS person 
LEFT JOIN
	 tb_user AS user on person.id_user = user.id 
LEFT JOIN
	tb_phone as phone on person.id = phone.id_person
LEFT JOIN
	tb_address as address on person.id = address.id_person
;

#GENERAL CLIENT QUERY
SELECT 
	* 
FROM 
	tb_client AS client
LEFT JOIN
	 tb_contact AS contact on client.id = contact.id_client
LEFT JOIN
	tb_phone as phone on client.id = phone.id_client
LEFT JOIN
	tb_address as address on client.id = address.id_client
LEFT JOIN
	tb_email as email on client.id = email.id_client
LEFT JOIN
	tb_segment as segment on client.id = segment.id_client
;	

#TABLES
#user
SELECT * FROM tb_user;
select * from tb_person;

#compliment
select * from tb_phone;
select * from tb_address;
select * from tb_email;

#client
select * from tb_client;
select * from tb_segment;
select * from tb_contact;

#opportunity
select * from tb_opportunity;
select * from tb_involvedTeam;
select * from tb_competitor;
select * from tb_product;
select * from tb_upload;

SET SQL_SAFE_UPDATES = 0;
UPDATE `dbSalesCenter`.`tb_opportunity`
SET
	`history` = 'Admin - 08/10/2018 19:30:  descrição, relato, histórico || '
;

#permission
SELECT * FROM tb_user;
select * from tb_userGroup;
select * from tb_permission;

#GENERAL PERMISSION QUERY
select 
	p.name
    ,u.login
	,g.name
    ,pm.screen
    ,pm.action_create
    ,pm.action_delete
    ,pm.action_disable
    ,pm.action_enable
    ,pm.action_read
    ,pm.action_update
from 
	tb_person as p
INNER JOIN
	 tb_user AS u on p.id_user = u.id 
inner join
	tb_userGroup as g on u.id_userGroup = g.id
inner join
	tb_permission as pm on g.id = pm.id_userGroup
WHERE
	p.id = 2
;

#HELP
SELECT * FROM tb_help;

#LOG
SELECT * FROM tb_log;

#COMPENSATION
SELECT * FROM tb_compensation;

SET SQL_SAFE_UPDATES = 0;
UPDATE `dbSalesCenter`.`tb_log`
SET
	`field` = 'AMOUNT'
;
    
