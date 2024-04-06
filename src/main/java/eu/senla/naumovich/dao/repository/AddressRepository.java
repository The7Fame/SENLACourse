package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> getAll();

    Address getById(Address address);

    Address update(Address address);

    Address create(Address address);

    void delete(Address address);
}
