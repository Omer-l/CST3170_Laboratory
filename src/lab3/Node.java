package lab3;

import java.util.ArrayList;

public class Node<T> {
    private T data;
    private Node<T> parent;
    private ArrayList<Node<T>> children;

    public Node(T data) {
        this.data = data;
    }

    public Node() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public ArrayList<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node<T>> children) {
        this.children = children;
    }
}
