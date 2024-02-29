package cn.arcy.jportal.jpa.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.Nullable;

/**
 * 自定义JPA工厂类，用于实现自定义的SimpleJpaRepository
 * 目前实现：当继承了软删除的实体类，则实现软删除的SimpleJpaRepository
 * 否则实现原生的SimpleJpaRepository
 */
public class BaseJpaRepositoryFactoryBean<T extends Repository<S, ID>, S, ID> extends JpaRepositoryFactoryBean<T, S, ID> {

    private EntityPathResolver entityPathResolver;
    private EscapeCharacter escapeCharacter = EscapeCharacter.DEFAULT;
    private JpaQueryMethodFactory queryMethodFactory;

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public BaseJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Autowired
    public void setEntityPathResolver(ObjectProvider<EntityPathResolver> resolver) {
        this.entityPathResolver = resolver.getIfAvailable(() -> SimpleEntityPathResolver.INSTANCE);
    }

    @Autowired
    public void setQueryMethodFactory(@Nullable JpaQueryMethodFactory factory) {

        if (factory != null) {
            this.queryMethodFactory = factory;
        }
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        BaseJpaRepositoryFactory baseJpaRepositoryFactory = new BaseJpaRepositoryFactory(entityManager);
        baseJpaRepositoryFactory.setEntityPathResolver(entityPathResolver);
        baseJpaRepositoryFactory.setEscapeCharacter(escapeCharacter);

        if (queryMethodFactory != null) {
            baseJpaRepositoryFactory.setQueryMethodFactory(queryMethodFactory);
        }

        return baseJpaRepositoryFactory;
    }


}
