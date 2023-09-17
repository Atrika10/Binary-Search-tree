public class RangeSum {
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

    
     public static int rangeSumBST(Node root, int low, int high) {
        // base case
        if (root == null) {
            return 0;
        }
        // Initialize sum for the current subtree
        int sum =0;

        // if root lies in the range
        if (low <= root.data && root.data <= high) {
            sum += root.data;// Add the value of the current node to the sum
        }

        // Recursively calculate the sum for left and right subtrees
        sum += rangeSumBST(root.left, low, high);
        sum += rangeSumBST(root.right, low, high);

        return sum;// Return the sum for the current subtree
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        
        root.right = new Node(15);
        root.right.right = new Node(18);
        System.out.println("range sum " + rangeSumBST(root, 7, 15));
    }
}
