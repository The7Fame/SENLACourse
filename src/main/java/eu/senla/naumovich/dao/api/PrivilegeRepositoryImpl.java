package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.entities.Privilege;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PrivilegeRepositoryImpl implements PrivilegeRepository {
    final List<Privilege> privileges = new ArrayList<>();
    @Override
    public List<Privilege> getAll() {
        return privileges;
    }

    @Override
    public Privilege getById(Privilege privilege) {
        return privileges.stream()
                .filter(p -> p.getId() == privilege.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Privilege update(Privilege privilege) {
        for(Privilege p : privileges){
            if(privilege.getId() == p.getId()){
                p.setPrivilegeName(privilege.getPrivilegeName());
                return p;
            }
        }
        return null;
    }

    @Override
    public Privilege create(Privilege privilege) {
        privileges.add(privilege);
        return privilege;
    }

    @Override
    public void delete(Privilege privilege) {
        privileges.removeIf(p -> p.getId() == privilege.getId());
    }
}
