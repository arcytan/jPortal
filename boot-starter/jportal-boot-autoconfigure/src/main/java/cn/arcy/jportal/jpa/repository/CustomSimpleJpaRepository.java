package cn.arcy.jportal.jpa.repository;

import cn.arcy.jportal.jpa.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

public class CustomSimpleJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    private final EntityManager em;

    private final JpaEntityInformation<T, ?> entityInformation;

    public CustomSimpleJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
        this.entityInformation = entityInformation;
    }

    public CustomSimpleJpaRepository(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S extends T> S save(S entity)
    {
        Assert.notNull(entity, "Entity must not be null");
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            em.refresh(entity);
            ID id = (ID)entityInformation.getId(entity);
            Assert.notNull(id, "ID为空，不能保存！");
            return (S)findById(id).orElse(entity);
        } else {
            return em.merge(entity);
        }
    }
}
