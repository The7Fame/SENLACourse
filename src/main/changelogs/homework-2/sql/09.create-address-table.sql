CREATE TABLE addresses (
     id bigserial primary key,
     city varchar(25) not null,
     street varchar(25) not null,
     index integer not null,
     publisher_id integer,
     foreign key(publisher_id) references publishers(id)
)
