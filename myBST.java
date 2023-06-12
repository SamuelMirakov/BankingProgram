/*	Samuel Mirakov
 * 	Project 1
 * 	CSCI 313 - Anne Smith-Thompson 
 */
// Project 1- PART 1
public class myBST {
  public btNode root = new btNode();
  Comparable<Customer> c1;

  // basic constructor
  public myBST() {
    root = null;
  }

  public btNode getRoot() {
    return this.root;
  }

  // Called from Main method and is used to call the private Recursive Search
  public btNode search(myBST tree, Customer other) {
    {
      return searchRecursive(tree.root, other);
    }
  }

  private btNode searchRecursive(btNode t, Customer other) {
    if (t != null) {

      if (other.compareTo(t.data) == 0) {
        return t;
      }

      else if (other.compareTo(t.data) < 0) {
        return searchRecursive(t.left, other);
      }

      else if (other.compareTo(t.data) > 0) {
        return searchRecursive(t.right, other);
      }

    }
    return null;

  }

  public void insert(myBST treeDB, btNode newNode) {
    if (treeDB.root == null) {
      treeDB.root = newNode;
    } else {
      treeDB.insertRec(treeDB.root, newNode);
    }
  }
  
  private void insertRec(btNode root, btNode newNode) {

    if ((newNode.data).compareTo(root.data) < 0) {

      if (root.left == null) {
        root.left = newNode;
      } else {
        // go left

        insertRec(root.left, newNode);
      }

    } else {
      if ((newNode.data).compareTo(root.data) > 0)

      {// if(t.left == null){
        if (root.right == null) {
          root.right = newNode;
        } else {
          // t.left = new btNode(data);

          // go right
          insertRec(root.right, newNode);
        }
      }
      // insert new Node into Binary tree
      // root = newNode;
    }
    // }

  }

  // To find the minimum value of the BST

  public Customer min(btNode root) {
    Customer min = root.data;
    while (root.left != null) {
      min = root.left.data;
      root = root.left;
    }
    return min;
  }

  public btNode parentNode(myBST tree, btNode node) {
    return parentNodeRecursive(tree.root, node);
  }

  private btNode parentNodeRecursive(btNode SubTreeRoot, btNode node) {
    if (SubTreeRoot == null) {
      return null;
    }

    if (SubTreeRoot.left == node || SubTreeRoot.right == node) {
      return SubTreeRoot;
    }

    if (node.data.compareTo(SubTreeRoot.data) < 0) {
      return parentNodeRecursive(SubTreeRoot.left, node);
    }

    return parentNodeRecursive(SubTreeRoot.right, node);
  }

  public void delete(myBST tree, Customer customer) {
    btNode newNode = search(tree, customer);
    btNode parent = parentNode(tree, newNode);
    deleteRecursive(tree, parent, newNode);
  }

  private boolean deleteRecursive(myBST tree, btNode parent, btNode node) {
    if (node == null) {
      return false;
      // parent node with two children
    }

    if (node.left != null && node.right != null) {
      btNode SuccNode = node.right;
      btNode SuccParent = node;
      while (SuccNode.left != null) {
        SuccParent = SuccNode;
        SuccNode = SuccNode.left;
      }
      node = SuccNode;
      deleteRecursive(tree, SuccParent, SuccNode);
    } else if (node == tree.root) {
      // root with at most 1 child
      if (node.left != null) {
        tree.root = node.left;
      } else {
        tree.root = node.right;
      }
    } else if (node.left != null) {
      // parent node with left child only
      if (parent.left == node) {
        parent.left = node.left;
      } else {
        parent.right = node.left;
      }
    } else {
      // parent node with right child only

      if (parent.left == node) {
        parent.left = node.right;
      } else {
        parent.right = node.right;
      }
    }

    return true;
  }

  public void display(btNode node) {
    if (node != null) {
      display(node.left);
      System.out.println(node.data.getFirst());
      display(node.right);
    }
