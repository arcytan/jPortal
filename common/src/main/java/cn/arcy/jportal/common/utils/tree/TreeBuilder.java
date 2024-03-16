package cn.arcy.jportal.common.utils.tree;

import cn.arcy.jportal.common.utils.function.FunctionSerializable;
import cn.arcy.jportal.common.utils.lambda.LambdaInfo;
import cn.arcy.jportal.common.utils.lambda.LambdaUtil;
import cn.hutool.core.lang.Assert;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class TreeBuilder<T, ID> {

    private final List<T> data;

    private final FunctionSerializable<T, ID> idField;

    private final FunctionSerializable<T, ID> parentField;

    private LambdaInfo<T> idFieldInfo;

    private LambdaInfo<T> parentFieldInfo;

    @Getter
    private List<TreeModel<T, ID>> treeModels;

    private Map<ID, TreeModel<T, ID>> childrenMap = new ConcurrentHashMap<>();

    public TreeBuilder(List<T> data, FunctionSerializable<T, ID> idField, FunctionSerializable<T, ID> parentField)
    {
        this.data = data;
        this.idField = idField;
        this.parentField = parentField;
        this.init();
    }

    public List<TreeModel<T, ID>> toTree()
    {
        List<TreeModel<T, ID>> treeModels = this.getTreeModels();
        List<TreeModel<T, ID>> rootTreeModels = new ArrayList<>();
        treeModels.forEach(item -> {
            item.setChildren(getChildrenById(item.getId()));
            if (isRootNode(item)) {
                rootTreeModels.add(item);
            }
        });

        return rootTreeModels;
    }

    public List<TreeModel<T, ID>> getChildrenById(ID id)
    {
        return this.treeModels.stream().filter(Objects::nonNull).filter(item -> Objects.equals(item.getParentId(), id)).collect(Collectors.toList());
    }

    private void init()
    {
        this.idFieldInfo = LambdaUtil.parseWithCache(idField);
        Assert.notNull(idField, "获取ID列失败！");
        this.parentFieldInfo = LambdaUtil.parseWithCache(parentField);
        Assert.notNull(parentField, "获取父ID列失败！");
        this.treeModels = this.toTreeModel(this.data);
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
                treeModel.setInfo(item);
                return treeModel;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    private boolean isRootNode(TreeModel<T, ID> node)
    {
        if (Objects.isNull(node.getParentId())) {
            return false;
        }

        //根据getParentId的类型，进行类型转换后再判断
        if (node.getParentId() instanceof Long) {
            return node.getParentId().equals(0L);
        } else if (node.getParentId() instanceof Integer) {
            return node.getParentId().equals(0);
        }

        return false;
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
