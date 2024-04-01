package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Privilege;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PrivilegeRepositoryImpl implements PrivilegeRepository{
    final List<Privilege> privileges = new ArrayList<>();
    @Override
    public List<Privilege> getAll() {
        return privileges;
    }

    @Override
    public Privilege getById(Privilege privilege) {
        for(Privilege p : privileges){
            if(p.getId() == privilege.getId()){
                return p;
            }
        }
        return null;
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
        for (int i = 0; i < privileges.size(); i++) {
            if (privilege.getId() == privileges.get(i).getId()) {
                privileges.remove(i);
            }
        }
    }
}
