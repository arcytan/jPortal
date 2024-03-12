package cn.arcy.jportal.portal.util.tree;

import lombok.Getter;

import java.util.List;

public class TreeModel<T, ID> {

    @Getter
    private ID id;

    @Getter
    private ID parentId;

    @Getter
    private T info;

    @Getter
    private List<TreeModel<T, ID>> children;

    public void setId(ID id)
    {
        this.id = id;
    }

    public void setParentId(ID parentId)
    {
        this.parentId = parentId;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public void setChildren(List<TreeModel<T, ID>> children) {
        this.children = children;
    }

    public void setChildrenItem(TreeModel<T, ID> item)
    {
        this.children.add(item);
    }
}
