package cn.arcy.jportal.jpa.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

public class BaseJpaRepositoryFactory extends JpaRepositoryFactory {

    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
    public BaseJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        System.out.println(information.getDomainType());
        return super.getTargetRepository(information, entityManager);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        System.out.println("ddd：" + metadata.getDomainType());
        return super.getRepositoryBaseClass(metadata);
    }
}
