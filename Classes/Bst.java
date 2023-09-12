public class Bst{
    public static class Node{
        int value;
        Node left;
        Node right;
        Node(int val){
            this.value = val;
            this.left = null;
            this.right = null;
        }
    }

    // insert function will return root for each values
    public static Node insert(Node root, int val){
        // first case
        if (root == null) {
            // create new node
            Node newNode = new Node(val);
            root = newNode;     // newNode is my root
            return root;
        }

        //In other case
        if (root.value > val) {
            // left subtree
            root.left = insert(root.left, val); // Whatever answer we'll get, it will be stored in root.left
        }else{
            root.right = insert(root.right, val);
        }
        return root;
    }

    // print bst's value in inOrder fashion
    public static void inOrder(Node root){      // left.data -> root.data -> right.data
        // base case
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.value + "-> ");
        inOrder(root.right);
    }

    // Search a key in BST
    // if it exist return true else return false
    // Time complexity will be O(H) where H is the height of the Binary Search Tree
    public static boolean searchKeyInBST(Node root, int key){
        // base case
        if (root == null) {
            return false;       // in any case if I found root = null means I've searched all node & didn't get my key
        }
        if (root.value == key) {
            return true;
        }else if(key > root.value){
            // I'll return what my right subtree return to me 
            return searchKeyInBST(root.right, key);
        }else{
            return searchKeyInBST(root.left, key);  // if my key is less that root.value
        }
    }

    public static void main(String args[]){
        int values[] = {5,4,6,1,2,8};
        Node root = null;

        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        // print BST
        // inOrder(root);
        // System.out.println("null");     // 1-> 2-> 4-> 5-> 6-> 8-> null

        System.out.println(searchKeyInBST(root, 16));

    }
}