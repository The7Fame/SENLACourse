CREATE TABLE reviews (
     id bigserial primary key,
     text varchar(255),
     rating integer,
     user_id bigint,
     book_id bigint,
     foreign key(user_id) references users(id) on delete cascade,
     foreign key(book_id) references books(id) on delete cascade
)
