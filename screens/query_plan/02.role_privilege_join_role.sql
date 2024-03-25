SELECT * FROM roles_privileges rp
JOIN roles r ON rp.role_id = r.id;
