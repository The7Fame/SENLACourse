CREATE TABLE authors_books (
    author_id bigint,
    book_id bigint,
    foreign key (author_id) references authors(id) on delete cascade,
    foreign key (book_id) references books(id) on delete cascade,
    primary key (author_id, book_id)
)
