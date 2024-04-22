package eu.senla.naumovich.dao.repository.common;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Transactional(readOnly = true)
public abstract class AbstractDao<K, T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = query.from(getEntityClass());
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    };

    public T getById(K id) {
        return entityManager.find(getEntityClass(), id);
    };

    @Transactional
    public T update(T object) {
        return entityManager.merge(object);
    };

    @Transactional
    public void create(T object) {
        entityManager.merge(object);
    };

    @Transactional
    public void delete(T object) {
        entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));
    };
}
