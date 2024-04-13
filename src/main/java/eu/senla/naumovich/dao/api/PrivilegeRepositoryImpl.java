package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Privilege;
import org.springframework.stereotype.Repository;

@Repository
public class PrivilegeRepositoryImpl extends AbstractDao<Long, Privilege> implements PrivilegeRepository {
    @Override
    protected Class<Privilege> getEntityClass() {
        return Privilege.class;
    }
}
