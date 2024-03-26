CREATE TABLE orders (
     id bigserial primary key,
     order_date timestamp,
     total_price decimal(5,2),
     user_id integer,
     foreign key(user_id) references users(id)
)
