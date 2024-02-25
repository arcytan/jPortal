package cn.arcy.jportal.jpa.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SoftDeleteRepository<T, ID> extends BaseRepository<T, ID> {

    @Override
    long delete(Specification<T> spec);
}
