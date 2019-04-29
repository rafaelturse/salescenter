USE dbsalescenter;

INSERT INTO `dbsalescenter`.`tb_phone`(
	`id`, `lastUpdate`, `registration`
	,`ddd`, `number`, `phoneType`
    ,`id_user`
    ,`status`
)
VALUES(
	1, current_timestamp(), current_date()
    ,'11', '946188509', 1
    ,1
    ,1
);

SELECT * FROM dbsalescenter.tb_phone;