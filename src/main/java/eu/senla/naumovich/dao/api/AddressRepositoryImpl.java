package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.AddressRepository;
import eu.senla.naumovich.entities.Address;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class AddressRepositoryImpl implements AddressRepository {
    final List<Address> addresses = new ArrayList<>();
    @Override
    public List<Address> getAll() {
        return addresses;
    }

    @Override
    public Address getById(Address address) {
        return addresses.stream()
                .filter(a -> a.getId() == address.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Address update(Address address) {
        for(Address a : addresses){
            if(address.getId() == a.getId()){
                a.setIndex(address.getIndex());
                return a;
            }
        }
        return null;
    }

    @Override
    public Address create(Address address) {
        addresses.add(address);
        return address;
    }

    @Override
    public void delete(Address address) {
        addresses.removeIf(a -> a.getId() == address.getId());
    }
}
