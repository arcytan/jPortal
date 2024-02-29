package cn.arcy.jportal.jpa.repository;

import cn.arcy.jportal.jpa.entity.SoftDeleteEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

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

    @Override
    public long delete(Specification<T> spec) {

        return super.delete(spec);
    }
}
