class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int l = depth(root.left);
        int r = depth(root.right);
        return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(l - r) <= 1);
        
    }
    
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}
