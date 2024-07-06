CREATE TABLE carts (
     id bigserial primary key,
     user_id bigint,
     book_id bigint,
     foreign key(user_id) references users(id) on delete cascade,
     foreign key(book_id) references books(id) on delete cascade,
     unique (user_id, book_id)
)
