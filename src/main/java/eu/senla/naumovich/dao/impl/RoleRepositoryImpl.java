package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends AbstractDao<Long, Role> implements RoleRepository {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
