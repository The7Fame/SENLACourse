CREATE TABLE reviews (
     id bigserial primary key,
     text varchar(255),
     rating integer,
     user_id integer,
     book_id integer,
     foreign key(user_id) references users(id),
     foreign key(book_id) references books(id)
)
