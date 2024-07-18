CREATE TABLE books (
     id bigserial primary key,
     title varchar(25) unique not null,
     price decimal(5, 2) not null,
     isbn varchar(13) unique not null,
     genre_id bigint,
     publisher_id bigint,
     foreign key(genre_id) references genres(id) on delete cascade,
     foreign key(publisher_id) references publishers(id) on delete cascade
)
