public class ClosestElement {
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

    public static int minDiff(Node  root, int K) { 
        // base case
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        // calculate the difference between root & K
        int a = Math.abs(root.data - K); 

        // Recursively calculate the min diff for left and right subtrees
        int b = minDiff(root.left, K);
        int c = minDiff(root.right, K);

        return Math.min(a, Math.min(b,c));  // return min of root, min from left,min from right
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(6);

        root.right = new Node(11);
        root.right.right = new Node(20);
        System.out.println("Min difference : " + minDiff(root, 10));
    }
}
