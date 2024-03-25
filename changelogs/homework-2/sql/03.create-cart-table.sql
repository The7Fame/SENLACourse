CREATE TABLE carts (
     id bigserial primary key,
     user_id integer,
     book_id integer,
     foreign key(user_id) references users(id),
     foreign key(book_id) references books(id)
)
