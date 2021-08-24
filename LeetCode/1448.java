class Solution {
    int ans = 0;

    public int goodNodes(TreeNode root) {
        search(root, root.val);
        return ans;
    }

    private void search(TreeNode root, int currentMax) {
        if (root == null) {
            return;
        }
        if (root.val >= currentMax) {
            ans++;
        }
        search(root.left, Math.max(currentMax, root.val));
        search(root.right, Math.max(currentMax, root.val));
    }
}
