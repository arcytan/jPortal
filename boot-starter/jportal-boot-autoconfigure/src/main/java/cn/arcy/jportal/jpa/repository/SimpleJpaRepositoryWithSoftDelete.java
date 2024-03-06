package cn.arcy.jportal.jpa.repository;

import cn.arcy.jportal.jpa.entity.SoftDeleteEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class SimpleJpaRepositoryWithSoftDelete<T extends SoftDeleteEntity, ID> extends SimpleJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public SimpleJpaRepositoryWithSoftDelete(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public SimpleJpaRepositoryWithSoftDelete(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Transactional
    @Override
    public long delete(Specification<T> spec) {
        List<T> lists = findAll(spec);
        if (lists.isEmpty()) {
            return 0L;
        }

        return super.delete(spec);
    }

    @Transactional
    public void softDelete(T entity)
    {
        entity.setDeleted(true);
        entity.setDeletedAt(LocalDateTime.now());
        save(entity);
    }

    public long softDelete(List<T> entities)
    {
        return 1L;
    }
}
