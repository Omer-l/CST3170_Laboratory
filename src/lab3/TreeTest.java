package lab3;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @org.junit.jupiter.api.Test
    public void getRoot() {
        Node<String> node1 = new Node<>("F1");
        Node<String> node2 = new Node<>("1");
        ArrayList<Node<String>> children = new ArrayList<>();
        children.add(node1);
        children.add(node2);

        Tree<String> tree = new Tree<>("F4", children);

        System.out.println(tree.getRoot().getChildren().get(0).getData());
    }

}