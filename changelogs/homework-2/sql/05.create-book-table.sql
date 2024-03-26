CREATE TABLE books (
     id bigserial primary key,
     title varchar(25) unique not null,
     price decimal(5, 2) not null,
     isbn varchar(13) unique not null,
     genre_id integer,
     publisher_id integer,
     foreign key(genre_id) references genres(id),
     foreign key(publisher_id) references publishers(id)
)
