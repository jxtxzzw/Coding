class Solution {

    private HashMap<TreeNode, TreeNode> parent = new HashMap<>();
    private ArrayList<TreeNode> visited = new ArrayList<>();


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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
