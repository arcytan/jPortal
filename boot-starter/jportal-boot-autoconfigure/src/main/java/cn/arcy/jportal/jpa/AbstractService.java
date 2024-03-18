package cn.arcy.jportal.jpa;

import cn.arcy.jportal.common.page.PageInfo;
import cn.arcy.jportal.common.utils.ApplicationContextUtil;
import cn.arcy.jportal.jpa.repository.BaseRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;

public abstract class AbstractService<R extends BaseRepository<T, ID>, T, ID> {

    public abstract R getRepository();

    public Page<T> findAllWithPage(Example<T> example, Pageable page)
    {
        return this.getRepository().findAll(example, page);
    }

    public Page<T> findAllWithPage(Example<T> example, Sort sort)
    {

        return null;
    }

    public Page<T> findAllWithPage(Sort sort)
    {
        PageInfo pageInfo = this.getPageInfo();
        PageRequest pageRequest = PageRequest.of(pageInfo.getPage(), pageInfo.getPageSize());
        if (Objects.nonNull(sort)) {
            pageRequest = pageRequest.withSort(sort);
        }

        return this.getRepository().findAll(pageRequest);
    }

    public Page<T> findAllWithPage()
    {
        return findAllWithPage(null);
    }

    /*public T findAllWithPage(Specification<T> spec, Pageable page)
    {

    }

    public T findAllWithPage(Specification<T> spec, Sort sort)
    {

    }*/


    private PageInfo getPageInfo()
    {
        return ApplicationContextUtil.getBean(PageInfo.class);
    }

    private 
}
