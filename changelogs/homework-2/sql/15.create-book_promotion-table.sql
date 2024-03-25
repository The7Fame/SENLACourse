CREATE TABLE books_promotions (
     book_id integer,
     promotion_id integer,
     foreign key (book_id) references books(id),
     foreign key (promotion_id) references promotions(id),
     primary key (book_id, promotion_id)
)
