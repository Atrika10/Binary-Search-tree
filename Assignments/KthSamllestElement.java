import java.util.*;
public class KthSamllestElement {
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

    public static int size = 0;
    public static void addElement(Node root, PriorityQueue<Integer> pq){
        // base case
        if (root == null) {
            return;
        }
        pq.add(root.data); size++;
        addElement(root.left, pq);
        addElement(root.right, pq);
    }
    public static int KthSmallestElement(Node root, int K) {
        // step 1 add element in pq
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        addElement(root, pq);   // O(nlogn) where n is the number of node in the given bst

        // corner case,  if no such element exists return -1.
        if (size < K) {
            return -1;
        }
        // step 2
        while (!pq.isEmpty() && K > 1) {    // in worst case it will take O(nlogn),  where K is close to N, 
            pq.remove();
            K--;
        }
        return pq.remove();     // so the final TC => O(nlogn) & space complexity is O(n) for taking a pq [n =number of node in the given bst ]
    }
    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(6);

        root.right = new Node(11);
        root.right.right = new Node(20);

        System.out.println("Kth smallest element " + KthSmallestElement(root, 3));

    }
}
