CREATE TABLE users (
     id bigserial primary key,
     name varchar(25) not null,
     surname varchar(25) not null,
     email varchar(64) unique not null,
     role_id integer,
     foreign key(role_id) references roles(id)
)
