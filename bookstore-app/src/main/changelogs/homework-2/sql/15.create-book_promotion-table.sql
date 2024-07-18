CREATE TABLE books_promotions (
     book_id bigint,
     promotion_id bigint,
     foreign key (book_id) references books(id) on delete cascade,
     foreign key (promotion_id) references promotions(id) on delete cascade,
     primary key (book_id, promotion_id)
)
