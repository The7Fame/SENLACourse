package eu.senla.naumovich.dao.repository.common;

import java.util.List;
import java.util.Optional;

import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.exceptions.NoRecords;
import jakarta.persistence.TypedQuery;
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

    public List<T> getAll(int page, int size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = query.from(getEntityClass());
        query.select(root);
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        applyPagination(typedQuery, page, size);
        return typedQuery.getResultList();
    };

    public Optional<T> findById(K id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(), id));
    };

    @Transactional
    public T update(T object) {
        return entityManager.merge(object);
    };

    @Transactional
    public T create(T object) {
        return entityManager.merge(object);
    };

    @Transactional
    public void deleteById(K id) {
        Optional<T> entity = findById(id);
        entity.ifPresent(entityManager::remove);
    };

    public void applyPagination(TypedQuery<?> query, int page, int size){
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
    }
}
