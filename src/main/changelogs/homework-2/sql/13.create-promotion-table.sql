CREATE TABLE promotions (
     id bigserial primary key,
     promotion_name varchar(25) unique not null,
     percent decimal(4, 2) not null
)
