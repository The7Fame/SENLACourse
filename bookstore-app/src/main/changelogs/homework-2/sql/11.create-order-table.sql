CREATE TABLE orders (
     id bigserial primary key,
     order_date timestamp,
     total_price decimal(5,2),
     user_id bigint,
     foreign key(user_id) references users(id) on delete cascade
)
