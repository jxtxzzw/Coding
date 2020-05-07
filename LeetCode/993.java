import java.util.HashMap;

class Solution {

    HashMap<Integer, Integer> parents = new HashMap<>();
    HashMap<Integer, Integer> depth = new HashMap<>();

    private void preProcess(TreeNode root) {
        go(root, -1, 0);
    }

    private void go(TreeNode root, Integer parent, int d) {
        if (root == null) {
            return;
        }
        parents.put(root.val, parent);
        depth.put(root.val, d);
        go(root.left, root.val, d + 1);
        go(root.right, root.val, d + 1);
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        preProcess(root);
        return !parents.get(x).equals(parents.get(y)) && depth.get(x).equals(depth.get(y));
    }
}
