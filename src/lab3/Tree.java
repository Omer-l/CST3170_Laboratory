package lab3;

import java.util.ArrayList;

public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.setData(rootData);
        root.setChildren(new ArrayList<Node<T>>());
    }

    public Tree(T rootData, ArrayList<Node<T>> children) {
        root = new Node<T>();
        root.setData(rootData);
        root.setChildren(children);
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }
}
