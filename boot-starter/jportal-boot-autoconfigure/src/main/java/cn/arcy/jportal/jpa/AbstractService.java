package cn.arcy.jportal.jpa;

import cn.arcy.jportal.common.page.PageInfo;
import cn.arcy.jportal.common.utils.ApplicationContextUtil;
import cn.arcy.jportal.common.utils.PageUtil;
import cn.arcy.jportal.jpa.repository.BaseRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;

public abstract class AbstractService<R extends BaseRepository<T, ID>, T, ID> {

    public abstract R getRepository();

    public Page<R> findAllWithPage(Example<T> example, Pageable page, Class<R> toClass)
    {
        Page<T> pageObj = this.getRepository().findAll(example, page);
        return PageUtil.toPage(pageObj, toClass);
    }

    public Page<T> findAllWithPage(Example<T> example, Sort sort)
    {
        PageRequest pageRequest = getPageRequest();
        if (Objects.nonNull(sort)) {
            pageRequest = pageRequest.withSort(sort);
        }
        return this.getRepository().findAll(example, pageRequest);
    }

    public Page<T> findAllWithPage(Example<T> example)
    {
        return findAllWithPage(example, (Sort) null);
    }

    public Page<R> findAllWithPage(Example<T> example, Class<R> toClass)
    {
        Page<T> pageObj = findAllWithPage(example);
        return PageUtil.toPage(pageObj, toClass);
    }

    public Page<T> findAllWithPage(Sort sort)
    {
        PageRequest pageRequest = this.getPageRequest();

        if (Objects.nonNull(sort)) {
            pageRequest = pageRequest.withSort(sort);
        }

        return this.getRepository().findAll(pageRequest);
    }

    public Page<R> findAllWithPage(Sort sort, Class<R> targetClass)
    {
        Page<T> pageObj = findAllWithPage(sort);
        return PageUtil.toPage(pageObj, targetClass);
    }

    public Page<T> findAllWithPage()
    {
        return findAllWithPage((Sort) null);
    }

    public Page<R> findAllWithPage(Class<R> targetClass)
    {
        Page<T> pageObj = findAllWithPage();
        return PageUtil.toPage(pageObj, targetClass);
    }

    public Page<R> findAllWithPage(Specification<T> spec, Pageable pageable, Class<R> toClass)
    {
        Page<T> pageObj = this.getRepository().findAll(spec, pageable);
        return PageUtil.toPage(pageObj, toClass);
    }

    public Page<T> findAllWithPage(Specification<T> spec, Sort sort)
    {
        PageRequest pageRequest = getPageRequest();
        if (Objects.nonNull(sort)) {
            pageRequest = pageRequest.withSort(sort);
        }

        return this.getRepository().findAll(spec, pageRequest);
    }

    public Page<R> findAllWithPage(Specification<T> spec, Sort sort, Class<R> toClass)
    {
        Page<T> pageObj = findAllWithPage(spec, sort);
        return PageUtil.toPage(pageObj, toClass);
    }

    public Page<T> findAllWithPage(Specification<T> spec)
    {
        return findAllWithPage(spec, (Sort) null);
    }

    public Page<R> findAllWithPage(Specification<T> spec, Class<R> toClass)
    {
        return PageUtil.toPage(findAllWithPage(spec), toClass);
    }

    private PageRequest getPageRequest()
    {
        PageInfo pageInfo = ApplicationContextUtil.getBean(PageInfo.class);
        PageRequest pageRequest = PageRequest.of(pageInfo.getPage(), pageInfo.getPageSize());
        return pageRequest;
    }
}
