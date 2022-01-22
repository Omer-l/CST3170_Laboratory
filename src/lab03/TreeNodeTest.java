package lab03;

import org.junit.Assert;

class TreeNodeTest {

    @org.junit.jupiter.api.Test
    public void getChildren() {
        TreeNode<String> treeNode1 = new TreeNode<>("F4");
        TreeNode<String> treeNode2 = new TreeNode<>("F3", treeNode1);

        String expected = "F3";
        String actual = treeNode1.getChildren().get(0).getData();
        Assert.assertEquals(expected, actual);
    }

}