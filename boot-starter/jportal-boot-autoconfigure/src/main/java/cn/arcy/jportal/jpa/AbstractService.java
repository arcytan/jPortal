package cn.arcy.jportal.jpa;

import cn.arcy.jportal.jpa.repository.BaseRepository;

public abstract class AbstractService<T, ID> {

    public abstract BaseRepository<T, ID> getRepository();
}
