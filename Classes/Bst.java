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

    public static void main(String args[]) {
        int values[] = { 5, 4, 6, 1, 2, 8 };
        Node root = null;

        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        // print BST
        inOrder(root);
        System.out.println("null");
        deleteNode(root, 5);
        // print BST
        inOrder(root);
        System.out.println("null");
    }
}