CREATE TABLE roles_privileges (
     role_id bigint,
     privilege_id bigint,
     foreign key (role_id) references roles(id) on delete cascade,
     foreign key (privilege_id) references privileges(id) on delete cascade,
     primary key (role_id, privilege_id)
)
