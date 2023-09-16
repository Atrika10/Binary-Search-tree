public class LargestBST {
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

    public static int maxSize = 0;
    public static class Info{
        boolean isBST;
        int size;
        int min;
        int max;
        public Info(boolean isBST, int size, int min, int max){
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }
    public static Info sizeOfLargestBST(Node root){
        // base case
        if (root == null) {         // for null node it is always a valid bst
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo = sizeOfLargestBST(root.left);
        Info rightInfo = sizeOfLargestBST(root.right);
        // Now I'll calculate for root
        // Need 4 info from root also
        int size = leftInfo.size + rightInfo.size +1;
        int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));   // min from left, right & root
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));   // max from left, right & root

        //  1 more info left which is this BT is validBST or not?

        //check is root valid or not
        if (root.data < leftInfo.max || root.data > rightInfo.min) {    // this is invalid condition
            return new Info(false, size, min, max);
        }

        // if root is valid, then I've to check is my left & right subtree is also valid or not
        if (leftInfo.isBST && rightInfo.isBST) {
            // as this is a valid condition so I'll compare the size of the current node.size with maxSize
            maxSize = Math.max(maxSize, size);
            return new Info(true, size, min, max); 
        }

        return new Info(false, size, min, max); // else this is inValid bst
    }
    public static void main(String[] args) {
        /*
         *          50
         *        /    \
         *      30      60
         *     /  \     / \
         *    5    20  45  70
         *                /  \
         *               65   80
         */
        /*
         * Largest bst size is = 5
         *        60
         *       / \
         *      45  70
         *         /  \
         *        65   80
         */

        Node root = new Node(50);
        root.left = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(20);

        root.right = new Node(60);
        root.right.left = new Node(45);
        root.right.right = new Node(70);
        root.right.right.left = new Node(65);
        root.right.right.right = new Node(80);

        sizeOfLargestBST(root);
        System.out.println(maxSize);
    }
}
