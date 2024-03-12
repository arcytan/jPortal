package cn.arcy.jportal.portal.util.tree;

import cn.hutool.core.lang.func.Func;

import java.util.List;
import java.util.function.Function;

public class TreeBuilder<T, ID> {

    private final List<T> data;

    private final Function<T, ID> field;

    public TreeBuilder(List<T> data, Function<T, ID> field)
    {
        this.data = data;
        this.field = field;
    }



    private 
}
