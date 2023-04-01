//Implemented a Binary Search Tree in java with Inorder Traversal , Search and Deletion of node

import java.util.*;

public class BST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }
        if (root.data > val) { // left side
            root.left = insert(root.left, val);
        } else { // right side
            root.right = insert(root.right, val);
        }
        return root;
    }

    // for traversing
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.data > key) {
            return search(root.left, key);
        }

        else if (root.data == key)
            return true;

        else {
            return search(root.right, key);
        }
    }

    public static Node delete(Node root, int val) {
        if (root.data > val) {
            root.left = delete(root.left, val);
        } else if (root.data < val) {
            root.right = delete(root.right, val);
        } else { // this case is when we reached the node to be deleted

            // no child
            if (root.left == null && root.right == null) {
                return null;
            }

            // single child
            if (root.left == null) { // means right exist
                return root.right;
            } else if (root.right == null) { // means left exist
                return root.left;
            }

            // two childs
            Node successorpointer = inorderSuccessor(root.right);
            root.data = successorpointer.data;
            root.right = delete(root.right, successorpointer.data);
        }
        return root;

    }

    public static Node inorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of tree ");
        int n = sc.nextInt();

        int values[] = new int[n];
        System.out.println("Enter values of tree ");
        Node root = null;
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
            root = insert(root, values[i]);
        }
        int choice;
        do {
            System.out.println("\nEnter Choice \n 1. Inorder Traversal \n 2. Search a Node \n 3. Delete a Node \n 4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Inorder traversal of BST-");
                    inorder(root);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Enter Node to search -");
                    int key = sc.nextInt();
                    if (search(root, key)) {
                        System.out.println("Found key");
                    } else
                        System.out.println("Not Found");
                    break;
                case 3:
                    System.out.println("Enter Node to delete- ");
                    int del = sc.nextInt();
                    delete(root, del);
                    System.out.println("After deleting - ");
                    inorder(root);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong Choice");

            }
        } while (choice != 4);
        sc.close();
    }
}