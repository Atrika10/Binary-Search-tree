import java.util.*;
public class BstToBalancedBST {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    public static void preOrder(Node root){
        // base case
        if (root == null) {
            return;
        }
        System.out.print(root.data +" ");
        preOrder(root.left);
        preOrder(root.right);
    } 
    static ArrayList<Integer> al = new ArrayList<>();
    public static void inOrder(Node root){
        // base case
        if (root == null) {
            return;
        }
        inOrder(root.left);
        //System.out.print(root.data +"-> ");
        al.add(root.data);
        inOrder(root.right);
    }
    public static Node createBalanceBST(ArrayList<Integer> al, int st, int end){
        if (st > end) {
            return null;
        }
        // find mid
        int mid = (st +end)/2;
        Node root = new Node(al.get(mid));
        root.left = createBalanceBST(al, st, mid-1);
        root.right = createBalanceBST(al, mid+1, end);
        return root;
    }
    // convert BST to balnaced BST
    public static Node convertBalancedBST(Node root){
        // find inorder seq & store in an Arraylist
        inOrder(root);  // will get an sorted AL

        // AL to balanced BST
        return createBalanceBST(al, 0, al.size()-1);
    }
    public static void main(String[] args) {
        /*
         *         8
         *        / \
         *       6   10  
         *      /      \
         *     5        11
         *    /           \
         *   3             12
         * Given BST
         * 
         */
        /*
         * Expected BST
         *         8
         *        / \
         *       5   11
         *      / \  / \
         *     3   6 10 12
         */

         Node root = new Node(8);
         root.left = new Node(6);
         root.left.left = new Node(5);
         root.left.left.left = new Node(3);
         root.right = new Node(10);
         root.right.right = new Node(11);
         root.right.right.right = new Node(12);

         preOrder(root);
         System.out.println();
         root = convertBalancedBST(root);
         preOrder(root);
    }
}
