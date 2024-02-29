insert
    into customer
    values (id, '8d0e2743-1ea6-47fb-93d4-c4fe21858733', '61673040845', 'Fátima Bianca Souza', 'fatima_bianca_souza@petrobrais.com.br', true, now(), now(), 0);

insert
    into product
    values (id, '799854f5-8ce5-4605-b6b8-857632c1abc5', 'Lanche', 'Um belo lanche', 'SANDWICH', 20.00, now(), now(), 0),
           (id, '48c5a140-0ed1-4c39-814a-f6c4e6dab326', 'Batata', 'Uma deliciosa batata', 'SIDE_DISH', 5.00, now(), now(), 0),
           (id, '25e8f2f4-3ae0-4be8-8d18-b6672f38feef', 'Bebida', 'Refrescante!!!', 'BEVERAGE', 3.50, now(), now(), 0);

insert
    into purchase
    values (id, '086dd216-2ef7-432c-a27b-4c840624c98d', 'f7g6', '1e494f11-968d-4e15-98ec-864f22e9b17c', (select id from customer where document = '61673040845'), 'PAID_SUCCESS', curdate(), now(), now(), 0),
           (id, 'e76cf4a5-0994-4729-972f-682529714120', 'f8g6', '1e494f11-968d-4e15-98ec-864f22e9b18c', (select id from customer where document = '61673040845'), 'CREATED', curdate(), now(), now(), 0),
           (id, 'ebbf8a00-3702-4758-84a9-a6cce4a5cbf6', 'f9g6', '1e494f11-968d-4e15-98ec-864f22e9b19c', (select id from customer where document = '61673040845'), 'PAID_ERROR', curdate(), now(), now(), 0),
           (id, '0f97ed15-2b11-48b5-bc08-fbac61e40c27', 'f0g6', '1e494f11-968d-4e15-98ec-864f22e9b20c', (select id from customer where document = '61673040845'), 'WAITING_MAKE', curdate(), now(), now(), 0),
           (id, '7d79859c-5831-48d0-920b-b9affda68074', 'f1g6', '1e494f11-968d-4e15-98ec-864f22e9b21c', (select id from customer where document = '61673040845'), 'MAKING', curdate(), now(), now(), 0),
           (id, '0480c7c0-7c81-430c-bfec-1a17e9915c06', 'f2g6', '1e494f11-968d-4e15-98ec-864f22e9b22c', (select id from customer where document = '61673040845'), 'MADE', curdate(), now(), now(), 0),
           (id, '0ef0fa3c-4147-4cd2-9afd-0971afdb31e8', 'f3g6', '1e494f11-968d-4e15-98ec-864f22e9b23c', (select id from customer where document = '61673040845'), 'DELIVERED', curdate(), now(), now(), 0),
           (id, '163e3c9f-5a97-4ba0-bab4-b69d11530771', 'f3g6', '1e494f11-968d-4e15-98ec-864f22e9b25c', (select id from customer where document = '61673040845'), 'WAITING_PAYMENT', curdate(), now(), now(), 0),
           (id, '754f95a6-1b54-42f8-ae6f-d4eb0ddd9df3', 'f9g6', '1e494f11-968d-4e15-98ec-864f22e9b24c', (select id from customer where document = '61673040845'), 'CREATED', curdate(), now(), now(), 0);

insert
    into purchase_item
    values ((select id from purchase where uuid = '086dd216-2ef7-432c-a27b-4c840624c98d'), (select id from product where name = 'Lanche'), 1, 1, 20.00, 2.00, now(), now(), 0),
           ((select id from purchase where uuid = '086dd216-2ef7-432c-a27b-4c840624c98d'), (select id from product where name = 'Batata'), 2, 1, 5.00, 0.00, now(), now(), 0),
           ((select id from purchase where uuid = '086dd216-2ef7-432c-a27b-4c840624c98d'), (select id from product where name = 'Bebida'), 3, 1, 3.50, 2.50, now(), now(), 0),
           ((select id from purchase where uuid = 'e76cf4a5-0994-4729-972f-682529714120'), (select id from product where name = 'Batata'), 1, 1, 5.00, 0.00, now(), now(), 0),
           ((select id from purchase where uuid = 'ebbf8a00-3702-4758-84a9-a6cce4a5cbf6'), (select id from product where name = 'Batata'), 1, 1, 5.00, 0.00, now(), now(), 0),
           ((select id from purchase where uuid = '0f97ed15-2b11-48b5-bc08-fbac61e40c27'), (select id from product where name = 'Batata'), 1, 1, 5.00, 0.00, now(), now(), 0),
           ((select id from purchase where uuid = '7d79859c-5831-48d0-920b-b9affda68074'), (select id from product where name = 'Batata'), 1, 1, 5.00, 0.00, now(), now(), 0),
           ((select id from purchase where uuid = '0480c7c0-7c81-430c-bfec-1a17e9915c06'), (select id from product where name = 'Batata'), 1, 1, 5.00, 0.00, now(), now(), 0),
           ((select id from purchase where uuid = '754f95a6-1b54-42f8-ae6f-d4eb0ddd9df3'), (select id from product where name = 'Batata'), 1, 1, 5.00, 0.00, now(), now(), 0),
           ((select id from purchase where uuid = '0ef0fa3c-4147-4cd2-9afd-0971afdb31e8'), (select id from product where name = 'Batata'), 1, 1, 5.00, 0.00, now(), now(), 0);

insert
    into payment
    values ((select id from purchase where uuid = '086dd216-2ef7-432c-a27b-4c840624c98d'), uuid(), uuid(), 'PAID', now(), now(), 0),
           ((select id from purchase where uuid = '0f97ed15-2b11-48b5-bc08-fbac61e40c27'), uuid(), uuid(), 'PAID', now(), now(), 0),
           ((select id from purchase where uuid = '7d79859c-5831-48d0-920b-b9affda68074'), uuid(), uuid(), 'PAID', now(), now(), 0),
           ((select id from purchase where uuid = '0480c7c0-7c81-430c-bfec-1a17e9915c06'), uuid(), uuid(), 'PAID', now(), now(), 0),
           ((select id from purchase where uuid = '0ef0fa3c-4147-4cd2-9afd-0971afdb31e8'), uuid(), uuid(), 'PAID', now(), now(), 0),
           ((select id from purchase where uuid = 'ebbf8a00-3702-4758-84a9-a6cce4a5cbf6'), uuid(), uuid(), 'ERROR', now(), now(), 0);