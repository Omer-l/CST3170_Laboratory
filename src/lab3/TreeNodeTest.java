package lab3;

import java.util.ArrayList;

class TreeNodeTest {

    @org.junit.jupiter.api.Test
    public void getRoot() {
        TreeNode<String> treeNode1 = new TreeNode<>("F1");
        TreeNode<String> node2 = new TreeNode<>("1");
        ArrayList<TreeNode<String>> children = new ArrayList<>();
        children.add(treeNode1);
        children.add(node2);

        TreeNode<String> node2Child1 = new TreeNode<>("F2");
        node2.getChildren().add(node2Child1);
    }

}