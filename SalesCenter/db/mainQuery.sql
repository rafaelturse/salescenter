use dbsalescenter;

### USER ###
INSERT INTO `dbsalescenter`.`tb_user` (
	`id`,`lastUpdate`,`registration`
    ,`name`,`lastName`,`birth`,`rg`,`cpf`
    ,`email`,`login`,`password`,`position`, `topUser`
    ,`status`
) VALUES (
	1, current_timestamp(), current_date()
    ,'admin', 'admin', '1979-08-01', '457651234', '08650094066'
    ,'admin@admin.com', 'turse', '123', 1, 1
    ,1 
);

SELECT * FROM dbsalescenter.tb_user;


#SELECT * FROM dbsalescenter.tb_address;
#SELECT * FROM dbsalescenter.tb_phone;