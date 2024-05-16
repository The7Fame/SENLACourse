CREATE TABLE payments (
     id bigserial primary key,
     status boolean default false,
     payment_date timestamp,
     total_price decimal(5,2),
     user_id integer,
     order_id integer,
     foreign key(user_id) references users(id),
     foreign key(order_id) references orders(id)
)
