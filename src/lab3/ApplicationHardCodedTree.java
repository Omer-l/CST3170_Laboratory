package lab3;

import java.util.Scanner;

public class ApplicationHardCodedTree {

    public static void main(String[] args) {
        FileReaderLab3 fileReader = new FileReaderLab3("lenseData.txt");
        DecisionTableRenderer decisionTable = new DecisionTableRenderer(fileReader.getData()); //render in the data.

        TreeNode<String> decisionTree = new TreeNode<>("F4");

        buildTree(decisionTree);

        //gets the decisions by prompting the user with questions.
        int[] decisions = prompt(decisionTree);
        int f1 = decisions[0];
        int f2 = decisions[1];
        int f3 = decisions[2];
        int f4 = decisions[3];

        //traverse decision tree.
        int category = traverseDecisionTree(decisionTree, f1, f2, f3, f4);

        System.out.println(categoryToString(category));
    }

    /**
     * prompts the user to enter the feature inputs.
     * @param decisionTree  decision tree to find the right lens class for the user.
     * @return              user's decisions, these are the indexes of the children in each TreeNode
     */
    public static int[] prompt(TreeNode<String> decisionTree) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the feature 1's input: ");
        int f1 = input.nextInt()-1;
        System.out.print("\nEnter the feature 2's input: ");
        int f2 = input.nextInt()-1;
        System.out.print("\nEnter the feature 3's input: ");
        int f3 = input.nextInt()-1;
        System.out.print("\nEnter the feature 4's input: ");
        int f4 = input.nextInt()-1;

        int[] decisions = {f1, f2, f3, f4};

        return decisions;
    }

    /**
     * builds tree, adds decisions in a breadth-first search order. Level by level, for each branch that contains a feature 'F'.
     * @param root
     */
    public static void buildTree(TreeNode<String> root) {
        /* LEVEL 0*/

        //F3 -> Nodes to create for F4 AKA root
        TreeNode<String> rootChild1 = new TreeNode<>("3", root);
        TreeNode<String> rootChild2 = new TreeNode<>("F3", root);

        /* LEVEL 1 */

        //F3 -> Nodes to create for F3's children
        TreeNode<String> F3Child1 = new TreeNode<>("F1", rootChild2);
        TreeNode<String> F3Child2 = new TreeNode<>("F2", rootChild2);

        /* LEVEL 2 */

        //F1 -> Nodes to create for F1's children
        TreeNode<String> F1Level2Child1 = new TreeNode<>("2", F3Child1);
        TreeNode<String> F1Level2Child2 = new TreeNode<>("2", F3Child1);
        TreeNode<String> F1Level2Child3 = new TreeNode<>("F2", F3Child1);

        //F2 -> Nodes to create for F2's children
        TreeNode<String> F2Level2Child1 = new TreeNode<>("1", F3Child2);
        TreeNode<String> F2Level2Child2 = new TreeNode<>("F1", F3Child2);

        /* LEVEL 3 */

        //F2 -> Nodes to create for F2's children
        TreeNode<String> F2Level3Child1 = new TreeNode<>("3", F1Level2Child3);
        TreeNode<String> F2Level3Child2 = new TreeNode<>("2", F1Level2Child3);

        //F1 -> Nodes to create for F1's children
        TreeNode<String> F1Level3Child1 = new TreeNode<>("1", F2Level2Child2);
        TreeNode<String> F1Level3Child2 = new TreeNode<>("F2", F2Level2Child2);
        TreeNode<String> F1Level3Child3 = new TreeNode<>("F2", F2Level2Child2);

        /* LEVEL 4 */

        //F2 -> Nodes to create for second branches' F2's children
        TreeNode<String> F2Level4Branch2Child1 = new TreeNode<>("1", F1Level3Child2);
        TreeNode<String> F2Level4Branch2Child2 = new TreeNode<>("3", F1Level3Child2);

        //F2 -> Nodes to create for third branches' F2's children
        TreeNode<String> F2Level4Branch3Child1 = new TreeNode<>("1", F1Level3Child3);
        TreeNode<String> F2Level4Branch3Child2 = new TreeNode<>("3", F1Level3Child3);
    }

    /**
     * traverses the tree based on the decisions made by the user.
     * @param treeRoot  root of the tree
     * @param f1    	feature 1's input
     * @param f2    	feature 2's input
     * @param f3    	feature 3's input
     * @param f4    	feature 4's input
     * @return          type of lens user should use.
     */
    public static int traverseDecisionTree(TreeNode<String> treeRoot, int f1, int f2, int f3, int f4) {
        int category = -1;

        TreeNode<String> currentTreeNode = treeRoot;

        while(currentTreeNode.getChildren().size() != 0) { //traverse decisions made until there are no children.
            switch(currentTreeNode.getData()) {
                case "F4": currentTreeNode = currentTreeNode.getChildren().get(f4); break;
                case "F3": currentTreeNode = currentTreeNode.getChildren().get(f3); break;
                case "F2": currentTreeNode = currentTreeNode.getChildren().get(f2); break;
                case "F1": currentTreeNode = currentTreeNode.getChildren().get(f1); break;
            }
        }

        category = Integer.parseInt(currentTreeNode.getData());

        return category;
    }

    /**
     * This function turns the category class into its respective lense type.
     * @param category	category class number
     * @return			lens type
     */
    public static String categoryToString(int category) {
        StringBuilder sb = new StringBuilder();

        if(category == 1)
            sb.append("The patient should be fitted with hard contact lenses");
        else if(category == 2)
            sb.append("The patient should not be fitted with contact lenses");
        else if(category == 3)
            sb.append("The patient should not be fitted with contact lenses");

        return sb.toString();
    }
}

