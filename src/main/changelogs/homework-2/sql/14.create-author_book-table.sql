CREATE TABLE authors_books (
    author_id integer,
    book_id integer,
    foreign key (author_id) references authors(id),
    foreign key (book_id) references books(id),
    primary key (author_id, book_id)
)
