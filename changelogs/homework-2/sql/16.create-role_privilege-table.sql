CREATE TABLE roles_privileges (
     role_id integer,
     privilege_id integer,
     foreign key (role_id) references roles(id),
     foreign key (privilege_id) references privileges(id),
     primary key (role_id, privilege_id)
)
