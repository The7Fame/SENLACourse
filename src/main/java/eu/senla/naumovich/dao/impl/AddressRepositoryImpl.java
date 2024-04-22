package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.AddressRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl extends AbstractDao<Long, Address> implements AddressRepository {
    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }
}
