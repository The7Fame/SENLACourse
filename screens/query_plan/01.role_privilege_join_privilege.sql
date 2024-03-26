SELECT * FROM roles_privileges rp
JOIN privileges p ON rp.privilege_id = p.id;
