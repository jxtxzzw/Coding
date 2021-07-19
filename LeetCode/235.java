
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    private HashMap<TreeNode, TreeNode> parent = new HashMap<>();
    private ArrayList<TreeNode> visited = new ArrayList<>();
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor_BST(root, p, q);
    }
    
    
    // Since it is a BST, we don't need to go through every node: Simply find the split point
    public TreeNode lowestCommonAncestor_BST(TreeNode root, TreeNode p, TreeNode q) {
        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        // Start from the root node of the tree
        TreeNode node = root;

        // Traverse the tree
        while (node != null) {

            // Value of ancestor/parent node.
            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                node = node.left;
            } else {
                // We have found the split point, i.e. the LCA node.
                return node;
            }
        }
        return null;
    }
    
    public TreeNode lowestCommonAncestor_Tree(TreeNode root, TreeNode p, TreeNode q) {
        parent.put(root, null);
        go(root);
        while (p != null) {
            visited.add(p);
            p = parent.get(p);
        }
        while (!visited.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
    
    private void go(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left, root);
            go(root.left);
        }
        if (root.right != null) {
            parent.put(root.right, root);
            go(root.right);
        }
    }
    
}

