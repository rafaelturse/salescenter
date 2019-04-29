USE dbsalescenter;

INSERT INTO `dbsalescenter`.`tb_address`(
	`id`, `lastUpdate`, `registration`
    ,`streetType` ,`street` ,`number` ,`compliment` ,`neighborhood`
    ,`cep` ,`city` ,`state` ,`country`
	,`id_user`
	,`status`
) VALUES (
	1, current_timestamp(), current_date()
    ,1 ,'Inácio dos Santos', 55, 'c3', 'Butantã'
    ,'05551040', 'São Paulo', 26, 'Brasil'
    ,1
    ,1
);

SELECT * FROM dbsalescenter.tb_address;