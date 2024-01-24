insert
    into customer
    values (id, '8d0e2743-1ea6-47fb-93d4-c4fe21858733', '61673040845', 'Fátima Bianca Souza', 'fatima_bianca_souza@petrobrais.com.br', true, now(), now(), 0);

insert
    into product
    values (id, '799854f5-8ce5-4605-b6b8-857632c1abc5', 'Lanche', 'Um belo lanche', 20.00, now(), now(), 0),
           (id, '48c5a140-0ed1-4c39-814a-f6c4e6dab326', 'Batata', 'Uma deliciosa batata', 5.00, now(), now(), 0),
           (id, '25e8f2f4-3ae0-4be8-8d18-b6672f38feef', 'Bebida', 'Refrescante!!!', 3.50, now(), now(), 0);

insert
    into purchase
    values (id, '086dd216-2ef7-432c-a27b-4c840624c98d', (select id from customer where document = '61673040845'), 'PAID', curdate(), now(), now(), 0);

insert
    into purchase_item
    values ((select id from purchase limit 1), (select id from product where name = 'Lanche'), 1, 20.00, 2.00, now(), now(), 0),
           ((select id from purchase limit 1), (select id from product where name = 'Batata'), 1, 5.00, 0.00, now(), now(), 0),
           ((select id from purchase limit 1), (select id from product where name = 'Bebida'), 1, 3.50, 2.50, now(), now(), 0);

insert
    into payment
    values (id, 'efd9203e-9843-43e1-a2a1-39089548a738', (select id from purchase limit 1), 'MERCADO_PAGO', curdate(), 'PAID', 24.00, now(), now(), 0);