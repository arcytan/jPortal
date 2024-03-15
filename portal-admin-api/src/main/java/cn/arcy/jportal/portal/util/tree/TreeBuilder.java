package cn.arcy.jportal.portal.util.tree;

import cn.arcy.jportal.common.utils.function.FunctionSerializable;
import cn.arcy.jportal.common.utils.lambda.LambdaInfo;
import cn.arcy.jportal.common.utils.lambda.LambdaUtil;
import cn.hutool.core.lang.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class TreeBuilder<T, ID> {

    private final List<T> data;

    private final LambdaInfo<T> idFieldInfo;

    private final LambdaInfo<T> parentFieldInfo;

    public TreeBuilder(List<T> data, FunctionSerializable<T, ID> idField, FunctionSerializable<T, ID> parentField)
    {
        this.data = data;
        this.idFieldInfo = LambdaUtil.parseWithCache(idField);
        Assert.notNull(idField, "获取ID列失败！");
        this.parentFieldInfo = LambdaUtil.parseWithCache(parentField);
        Assert.notNull(parentField, "获取父ID列失败！");
    }

    public List<TreeModel<T, ID>> toTree()
    {
        List<TreeModel<T, ID>> treeModels = this.toTreeModel(this.data);

        return null;
    }

    @SuppressWarnings("unchecked")
    private List<TreeModel<T, ID>> toTreeModel(List<T> data)
    {
        //将原生的列表数据，转化为TreeModel对象列表
        return data.stream().filter(Objects::nonNull).map(item -> {
            try {
                ID id = (ID) item.getClass().getMethod(this.getIdMethodName(item)).invoke(item);
                ID parentId = (ID) item.getClass().getMethod(this.getParentFieldMethodName(item)).invoke(item);
                TreeModel<T, ID> treeModel = new TreeModel<>();
                treeModel.setId(id);
                treeModel.setParentId(parentId);
                return treeModel;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    private String getIdMethodName(T clazz)
    {
        //判断是否同一个类型
        Assert.isAssignable(clazz.getClass(), this.idFieldInfo.getClazz());
        return this.idFieldInfo.getMethodName();
    }

    private String getParentFieldMethodName(T clazz)
    {
        Assert.isAssignable(clazz.getClass(), this.parentFieldInfo.getClazz());
        return this.parentFieldInfo.getMethodName();
    }
}
