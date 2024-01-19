create database if not exists purchase character set utf8mb4 collate utf8mb4_0900_ai_ci;

create user if not exists 'sys_purchase'@'%' identified with mysql_native_password by 'purchase_6#233zJMNTjkQb#sgps#';

grant all privileges on purchase.* to 'sys_purchase'@'%';

flush privileges;
