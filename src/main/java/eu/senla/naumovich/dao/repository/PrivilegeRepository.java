package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Privilege;

import java.util.List;

public interface PrivilegeRepository {
    List<Privilege> getAll();

    Privilege getById(Privilege privilege);

    Privilege update(Privilege privilege);

    Privilege create(Privilege privilege);

    void delete(Privilege privilege);
}
