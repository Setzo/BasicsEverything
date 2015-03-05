package controller;

import java.util.NoSuchElementException;

public class BST {

	public static final transient int PREORDER = 0;
	public static final transient int INORDER = 1;
	public static final transient int POSTORDER = 2;
	
	private class Node {
		
		Node parent = null;
		
		Node leftNode = null;
		Node rightNode = null;
		
		int value;
		//int level;
		
		protected Node(int value) {
			this.value = value;
		}
	}
	
	private Node root = null;
	
	public void insert(int value) {
		
		if(root == null) {
			root = new Node(value);
			
		} else {
			Node currentNode = root;
			Node parentNode = null;
			
			while(currentNode != null) {
				parentNode = currentNode;
				
				if(currentNode.value > value) {
					currentNode = currentNode.leftNode;
					
				} else {
					currentNode = currentNode.rightNode;
				}
			}
			
			if(parentNode.value > value) {
				parentNode.leftNode = new Node(value);
				parentNode.leftNode.parent = parentNode;
			} else {
				parentNode.rightNode = new Node(value);
				parentNode.rightNode.parent = parentNode;
			}
		}
	}
	
	public Node search(int value) throws NoSuchElementException{
		
		Node currentNode = root;
		
		while(currentNode != null && currentNode.value != value) {
			
			if(currentNode.value > value) {
				currentNode = currentNode.leftNode;
			} else {
				currentNode = currentNode.rightNode;
			}
			
			if(currentNode == null) {
				throw new NoSuchElementException("No such integer in the tree");
			}
		}
		
		return currentNode;
	}
	
	private Node min(Node node) {
		
		while(node.leftNode != null) {
			node = node.leftNode;
		}
		return node;
	}
	
	private Node max(Node node) {
	
		while (node.rightNode != null) {
			node = node.rightNode;
		}
		return node;
	}
	
	private Node successor(int value) throws NoSuchElementException{
		
		Node node = this.search(value);     

		if (node.rightNode != null) {
			
			node = node.rightNode;
			
			while (node.leftNode != null) {
				node = node.leftNode;
			}
			return node;
			
		} else if (node.rightNode == null && node != root && node != this.max(root)) {
			
			Node parent = node.parent;
			
			while (parent != root && parent.value < node.value) {
				parent = parent.parent;
			}
			return parent;
			
		} else {
			throw new NoSuchElementException("No parent");
		}
	}
	
	private Node predecessor(int value) throws NoSuchElementException{
		
		Node node = this.search(value);
		
		if (node.leftNode != null) {
			
			node = node.leftNode;
			
			while (node.rightNode != null) {
				node = node.rightNode;
			}
			return node;
		}

		else if (node.leftNode == null && node != root && node != this.min(root)) {
			
			Node parent = node.parent;
			
			while (parent != root && parent.value > node.value) {
				parent = parent.parent;
			}
			return parent;
			
		} else {
			throw new NoSuchElementException("No parent");
		}
	}
	
	public Node remove(int value) {
		
		Node node = this.search(value);
		Node parent = node.parent;
		Node tmp;

		if (node.leftNode != null && node.rightNode != null) {
			
			tmp = this.remove(this.successor(value).value);
			tmp.leftNode = node.leftNode;
			
			if (tmp.leftNode != null) {
				tmp.leftNode.parent = tmp;
			}
			tmp.rightNode = node.rightNode;

			if (tmp.rightNode != null) {
				tmp.rightNode.parent = tmp;
			}
			
		} else {
			tmp = (node.leftNode != null) ? node.leftNode : node.rightNode;
		}

		if (tmp != null) {
			node.parent = parent;
		}
		if (parent == null) {
			root = tmp;
			
		} else if (parent.leftNode == node) {
			parent.leftNode = tmp;
			
		} else {
			parent.rightNode = tmp;
		}
		
		return node;
	}
	
	public void printTree(Node node, final int orderConstant) {
		
		switch (orderConstant) {
			
		case PREORDER : {
			if (node != null) {
				
				System.out.print(node.value + ", ");
				
				if (node.leftNode != null) {
					System.out.print(node.leftNode.value + ", ");
					
				} else {
					System.out.print("-, ");
				}
				
				if (node.rightNode != null) {
					System.out.println(node.rightNode.value);
					
				} else {
					System.out.println("-");
				}

				printTree(node.leftNode, orderConstant);
				printTree(node.rightNode, orderConstant);
			}
			break;
		}
		case INORDER : {
			if(node != null) {
				
				printTree(node.leftNode, orderConstant);
				System.out.print(node.value + ", ");
				printTree(node.rightNode, orderConstant);
			}
			break;
		}
		case POSTORDER : {
			if (node != null) {
				
				printTree(node.leftNode, orderConstant);
				printTree(node.rightNode, orderConstant);
				
				System.out.print(node.value + ", ");
				
				if (node.leftNode != null) {
					System.out.print(node.leftNode.value + ", ");
					
				} else {
					System.out.print("-, ");
				}
				
				if (node.rightNode != null) {
					System.out.println(node.rightNode.value);
					
				} else {
					System.out.println("-");
				}
			}
			break;
		}
		}
	}
}
