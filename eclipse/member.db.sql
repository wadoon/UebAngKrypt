-- Alexander Weigl <weigla@fh-trier.de>
-- Date: 2011-05-02
-- Simple membership management with crypto functions on mysql
-- call: mysql -u <user> -p<password> < member.db.sql
--       for creation

drop database if exists krypt;
create database krypt;
use             krypt;

CREATE TABLE members (
  idx varchar(40)   not null primary key comment 'sha 1 key of lastname',
  data varchar(1000) not null unique     comment 'crypted data from person'
);

DELIMITER //

CREATE FUNCTION getMember (lastname varchar(40) )
RETURNS varchar(256)
BEGIN
	DECLARE tmp VARCHAR(1000);
	SELECT data FROM members 
	WHERE  idx = SHA1(lastname) INTO tmp;
	RETURN AES_DECRYPT(tmp,lastname);
END;//

CREATE PROCEDURE saveMember(IN lastname varchar(40) 
	                  , IN data varchar(256)   )
BEGIN
	DECLARE k VARCHAR(40);
	DECLARE v VARCHAR(1000);

	SET v= AES_ENCRYPT(data, lastname);
	SET k= SHA1(lastname);

	INSERT INTO members VALUES (k, v);
END;//

delimiter ;

CALL saveMember("Weigl", "Alexander Weigl -- Hornstr 11 -- 54294 Trier");
CALL saveMember("Wambach", "Tim Wambach -- Somewhere ...");
CALL saveMember("Kuenkler", "Andreas Kuenkler -- Somewhere ...");
CALL saveMember("Knor", "Konstantin Knor -- Somewhere ...");
CALL saveMember("Yuen", "Timmy Wai Hong Yuen -- Am Bahnhof, im schlimmen Viertel...");


SELECT * FROM members;

SELECT getMember("Weigl");
SELECT getMember("Yuen");
