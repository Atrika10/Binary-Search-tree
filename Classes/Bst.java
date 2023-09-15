import java.util.ArrayList;

public class Bst {
    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int val) {
            this.value = val;
            this.left = null;
            this.right = null;
        }
    }

    // insert function will return root for each values
    public static Node insert(Node root, int val) {
        // first case
        if (root == null) {
            // create new node
            Node newNode = new Node(val);
            root = newNode; // newNode is my root
            return root;
        }

        // In other case
        if (root.value > val) {
            // left subtree
            root.left = insert(root.left, val); // Whatever answer we'll get, it will be stored in root.left
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    // print bst's value in inOrder fashion
    public static void inOrder(Node root) { // left.data -> root.data -> right.data
        // base case
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.value + "-> ");
        inOrder(root.right);
    }

    // Time Comp. => O(n)
    public static void preOrder(Node root) {
        // base case
        if (root == null) {
            return;
        }
        System.out.print(root.value + "->");
        preOrder(root.left);
        preOrder(root.right);
    }

    // Time Comp. => O(n)
    public static void postOrder(Node root) {
        // base case
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + "->");
    }

    // Search a key in BST
    // if it exist return true else return false
    // Time complexity will be O(H) where H is the height of the Binary Search Tree
    public static boolean searchKeyInBST(Node root, int key) {
        // base case
        if (root == null) {
            return false; // in any case if I found root = null means I've searched all node & didn't get
                          // my key
        }
        if (root.value == key) {
            return true;
        } else if (key > root.value) {
            // I'll return what my right subtree return to me
            return searchKeyInBST(root.right, key);
        } else {
            return searchKeyInBST(root.left, key); // if my key is less that root.value
        }
    }

    /* where "h" is the height of the tree.
    In the worst case, the height of a BST can be O(n), where "n" is the number of nodes in the tree.
    so the worst-case time complexity for deleting a node in a BST is O(n)
    */
    // we will return root node
    public static Node deleteNode(Node root, int deleteNodeValue) {

        if (root.value > deleteNodeValue) { // if value exist in the left side
            root.left = deleteNode(root.left, deleteNodeValue);
        } 
        else if (root.value < deleteNodeValue) {
            root.right= deleteNode(root.right, deleteNodeValue);// if value exist in the right side
        } 
        else { // if I've to delete the root node
            // If I've to delete the root node, there will be 3 case
            // case 1 => leaf node delete
            if (root.left == null && root.right == null) {
                return null;
            }
            // case 2 => if it has 1 children
            if (root.left == null) {
                return root.right;  // if left node is null, will return right children to the parent node
            }
            if(root.right ==  null){
                return root.left;
            }

            // case 3 => if it has both children
            // we need to find inorder successor or inorder predecessor of that node which we've to delete
            Node inSucc = inOrderSuccessor(root.right, deleteNodeValue);
            root.value = inSucc.value;  // replace root value with IS value
            root.right = deleteNode(root.right, inSucc.value);

        }
        return root;
    }

    public static Node inOrderSuccessor(Node root, int deleteNodeValue){
        // I've to find left most node of right subtree
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // TC => O(n), where n is the number of node of BST

    public static void printInRange(Node root, int k1, int k2){
        // base case
        if (root == null) {
            return;
        }
        if(root.value >= k1 && root.value <= k2){   // we will print in inorder fashion
            printInRange(root.left, k1, k2);
            System.out.print(root.value +" ");
            printInRange(root.right, k1, k2);
        }else if(root.value < k1){                  // if the value of root is less that k1, we'll call right subtree
            printInRange(root.right, k1, k2);
        }else{                                      // else we'll call left subtree
            printInRange(root.left, k1, k2);
        }
    }

    // function to print all path
    public static void printPath(ArrayList<Integer> path){
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) +"-> ");
        }
        System.out.println("null");
    }

    // Print All possible path from root to leaf
    public static void printRoot2Leaf(Node root, ArrayList<Integer> path){
        // base case
        if (root == null) {
            return;
        }
        path.add(root.value);
        // Am i Standing in the laef node? if yes then print path
        if (root.left == null && root.right == null) {
            printPath(path);
        }
        printRoot2Leaf(root.left, path);
        printRoot2Leaf(root.right, path);
        path.remove(path.size()-1); // remove last element when it's completed it's left & right subtree

    }

    // Check is given BST is valid or not
    public static boolean isBSTValid(Node root, Node min, Node max){
        // base case
        if (root == null) {
            return true;
        }
        // for left subtree
        if (max != null && root.value >= max.value) {
            return false;
        }
        // for right subtree
        else if (min != null && root.value <= min.value) {
            return false;
        }
        return isBSTValid(root.left, min,root) 
                && isBSTValid(root.right, root, max);
    }

    // public static int checkBST(Node root, Node prev){
    //     if(root == null){
    //         return 1;
    //     }
    //     int left = checkBST(root.left, prev);
    //     if(prev != null && root.value <= prev.value){
    //         return 0;   // false
    //     }
    //     // update prev
    //     prev = root;
    //     int right = checkBST(root.right, prev);
    //     return (left == 1 && right == 1) ? 1 :0;
    // }
    // //Function to check whether a Binary Tree is BST or not.
    // public static boolean isBST(Node root)
    // {
    //     // code here.
    //     Node prev = null; 
    //     int ans = checkBST(root, prev);
    //     if (ans == 1) {
    //         return true;
    //     }else{
    //         return false;
    //     }
    // }

    public static void main(String args[]) {
        int values[] = { 1,2,3};
        Node root = null;

        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        
    }
}