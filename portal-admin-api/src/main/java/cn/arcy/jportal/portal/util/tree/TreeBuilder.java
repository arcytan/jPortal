package cn.arcy.jportal.portal.util.tree;

import cn.hutool.core.lang.func.Func;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class TreeBuilder<T, ID> {

    private final List<T> data;

    private final Supplier<ID> field;

    public TreeBuilder(List<T> data, Supplier<ID> field)
    {
        this.data = data;
        this.field = field;
    }



    private List<TreeModel<T, ID>> toTreeModels(List<T> data)
    {
        ArrayList<TreeModel<T, ID>> treeModels = new ArrayList<>();
        for (T item )
        treeModels.add(TreeModel.builder().id())
    }
}
