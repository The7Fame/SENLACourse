CREATE TABLE payments (
     id bigserial primary key,
     status boolean default false,
     payment_date timestamp,
     total_price decimal(5,2),
     user_id bigint,
     order_id bigint,
     foreign key(user_id) references users(id) on delete cascade,
     foreign key(order_id) references orders(id) on delete cascade
)
