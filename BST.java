import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BST {
	private TreeNode root = null;	
	private class TreeNode {  
		  
        private int key;  
        private TreeNode left;  
        private TreeNode right;  
        private TreeNode parent;  
  
        public TreeNode(int key, TreeNode left, TreeNode right,  
                TreeNode parent) {  
            this.key = key;  
            this.left = left;  
            this.right = right;  
            this.parent = parent;  
        }  
	}
	
	public boolean isEmpty() {  
        if (root == null) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
    
    public void add(int key) {  
        TreeNode parentNode = null;  
        TreeNode newNode = new TreeNode(key, null, null, null);  
        TreeNode node = root;  
        if (root == null) {  
            root = newNode;  
            return;  
        }  
        while (node != null) {  
            parentNode = node;  
            if (key < node.key) {  
            	node = node.left;  
            } else if (key > node.key) {  
            	node = node.right;  
            } else {  
                return;  
            }  
        }  
        if (key < parentNode.key) {  
            parentNode.left = newNode;  
            newNode.parent = parentNode;  
        } else {  
            parentNode.right = newNode;  
            newNode.parent = parentNode;  
        }  
  
    }

    public boolean remove(int key) {   
    	if (contains(key) == null) {  
            System.out.println("no such node!");  
            return false;  
        }  
    	TreeNode current = root;  
        TreeNode parent = root;  
        boolean isleft = true;  
        while (current.key != key) {  
            parent = current;  
            if (key < current.key) {  
                isleft = true;  
                current = current.left;  
            } else {  
                isleft = false;  
                current = current.right;  
            }  
        } 
        if (current.left == null && current.right == null) { 
            if (current == root) { 
                root = null;  
            } else if (isleft) { 
                parent.left = null;  
            } else { 
                parent.right = null;  
            }  
        } 
        //have no left child 
        else if (current.left == null) {                        
            if (current == root) {   
                root = current.right;  
            } else if (isleft) {  
                parent.left = current.right;  
            } else {  
                parent.right = current.right;  
            }  
        } 
        //have no right child
        else if (current.right == null) {                         
            if (current == root) {   
                root = current.left;  
            } else if (isleft) {  
                parent.left = current.left;  
            } else {  
                parent.right = current.left;  
            }  
        } 
        else {                                                        
            TreeNode successor = successor(current);  
            if(current == root){  
                root = successor;  
            }else if(isleft){  
                parent.left = successor;  
            }else{  
                parent.right = successor;  
            }  
            successor.left = current.left;  
        }  
        return true;  
    }
    
    //find the successor node
    public TreeNode successor(TreeNode node){  
    	TreeNode parent = node;  
        TreeNode successor = node;  
        TreeNode current = node.right;  
        while(current != null){  
            parent = successor;  
            successor = current;  
            current = current.left;  
        }  
          
        if(successor != node.right){  
            parent.left = successor.right;  
            successor.right = node.right;  
        }  
        return successor;  
    }
    
    
//    public TreeNode min(TreeNode root) {  
//        TreeNode node = root;  
//        while (node.left != null) {  
//            node = node.left;  
//        }  
//        return node;  
//    }
  
    public TreeNode contains(int key) {  
    	TreeNode node = root;  
        while (node != null && node.key != key) {  
            if (key < node.key) {  
            	node = node.left;  
            } else {  
            	node = node.right;  
            }  
        }  
        return node;  
    }
    
    public static void main(String[] args){
    	BST bst = new BST();
    	Scanner input = new Scanner(System.in);
    	
    	try {
		    Scanner in = new Scanner(new File("src/infile.dat"));
		    in.useDelimiter(" |, | ,|,");
		    while(in.hasNext()){
		    	bst.add(in.nextInt());
		    }
		    in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//    	System.out.println("Please input your delete value:");
//    	int value2 = input.nextInt();
//		bst.remove(value2);
    	
    	System.out.println("Please input your value:");
    	int value = input.nextInt();
    	if(bst.contains(value) == null){
    		System.out.println("No");
    	}
    	else{
    		System.out.println("Yes");
    	}
    	
    	input.close();  	
    }

}
