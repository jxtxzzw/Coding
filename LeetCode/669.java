// 树就是递归，但是这道题的递归有点难想到

class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        int val = root.val;
        if (low <= val && val <= high) {
            // This value is kept, so just recursively process its two children
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        }
        // This node is out of range, we need one of (or none of) its children
        if (val < low) {
            return trimBST(root.right, low, high);
        }
        if (val > high) {
            return trimBST(root.left, low, high);
        }
        return root;
    }
}
