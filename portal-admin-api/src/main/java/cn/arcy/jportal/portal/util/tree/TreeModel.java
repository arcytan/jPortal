package cn.arcy.jportal.portal.util.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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

    public static <T, ID> TreeModelBuilder<T, ID> builder()
    {
        return new TreeModelBuilder<>();
    }

    public static class TreeModelBuilder<T, ID> {

        private ID id;

        private ID parentId;

        private T info;

        private List<TreeModel<T, ID>> children;

        public TreeModelBuilder<T, ID> id(ID id)
        {
            this.id = id;
            return this;
        }

        public TreeModelBuilder<T, ID> parentId(ID parentId)
        {
            this.parentId = parentId;
            return this;
        }

        public TreeModelBuilder<T, ID> info(T info)
        {
            this.info = info;
            return this;
        }

        public TreeModelBuilder<T, ID> item(TreeModel<T, ID> item)
        {
            this.children.add(item);
            return this;
        }

        public TreeModelBuilder<T, ID> children(List<TreeModel<T, ID>> children)
        {
            this.children = children;
            return this;
        }

        public TreeModel<T, ID> build()
        {
            return new TreeModel<>(this.id, this.parentId, this.info, this.children);
        }
    }
}
