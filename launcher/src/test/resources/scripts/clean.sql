delete from payment where purchase_id > 0;
delete from purchase_item where purchase_id > 0;
delete from purchase where id > 0;
delete from product where id > 0;
delete from customer where id > 0;