package cn.arcy.jportal.portal.util.tree;

import cn.arcy.jportal.common.utils.function.FunctionSerializable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TreeBuilder<T, ID> {

    private final List<T> data;

    private final Function<T, ID> idField;

    private final Function<T, ID> parentField;

    public TreeBuilder(List<T> data, FunctionSerializable<T, ID> idField, FunctionSerializable<T, ID> parentField)
    {
        this.data = data;
        this.idField = idField;
        this.parentField = parentField;
    }



    /*private List<TreeModel<T, ID>> toTree(List<T> data)
    {
        ArrayList<TreeModel<T, ID>> tree = new ArrayList<>();
        //将原生的列表数据，转化为TreeModel对象列表
        data.stream().filter(Objects::nonNull).map(item -> {
            TreeModel.builder().id()
        })
    }*/
}
