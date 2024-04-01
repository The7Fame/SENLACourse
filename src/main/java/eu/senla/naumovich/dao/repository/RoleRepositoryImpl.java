package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository{
    final List<Role> roles = new ArrayList<>();
    @Override
    public List<Role> getAll() {
        return roles;
    }

    @Override
    public Role getById(Role role) {
        for(Role r : roles){
            if(r.getId() == role.getId()){
                return r;
            }
        }
        return null;
    }

    @Override
    public Role update(Role role) {
        for(Role r : roles){
            if(role.getId() == r.getId()){
                r.setRoleName(role.getRoleName());
                return r;
            }
        }
        return null;
    }

    @Override
    public Role create(Role role) {
        roles.add(role);
        return role;
    }

    @Override
    public void delete(Role role) {
        for (int i = 0; i < roles.size(); i++) {
            if (role.getId() == roles.get(i).getId()) {
                roles.remove(i);
            }
        }
    }
}
