package lab3;

import java.util.ArrayList;
//child 0 is input 1 - child 1 is input 2 - child 2 is input 3.
public class TreeNode<T> {
    private T data;
    private TreeNode<T> parent;
    private ArrayList<TreeNode<T>> children;

    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.parent.children.add(this); //The parent of this TreeNode will have this as it's child.
    }
    //no children, top of tree.
    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public ArrayList<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TreeNode<T>> children) {
        this.children = children;
    }
}
