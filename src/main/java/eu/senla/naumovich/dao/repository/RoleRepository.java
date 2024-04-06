package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> getAll();

    Role getById(Role role);

    Role update(Role role);

    Role create(Role role);

    void delete(Role role);
}
