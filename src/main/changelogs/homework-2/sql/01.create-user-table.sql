CREATE TABLE users (
     id bigserial primary key,
     name varchar(25) not null,
     surname varchar(25) not null,
     email varchar(64) unique not null,
     hash_password varchar(255) not null,
     balance decimal(5, 2) default 0,
     role_id bigint,
     foreign key(role_id) references roles(id) on delete cascade
)
