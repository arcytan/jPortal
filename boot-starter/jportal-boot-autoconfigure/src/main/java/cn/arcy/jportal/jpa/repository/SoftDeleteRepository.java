package cn.arcy.jportal.jpa.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends BaseRepository<T, ID> {

    @Override
    long delete(Specification<T> spec);
}
