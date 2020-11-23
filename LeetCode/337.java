class Solution {
    public int rob(TreeNode root) {
        int ct = rec(root, true);
        int cf = rec(root, false);
        return Math.max(ct, cf);
    }
    
    private int rec(TreeNode root, boolean could) {
        int money = 0;
        if (root != null) {
            if (could) {
                int l = 0, r = 0;
                money += root.val;
                l = rec(root.left, false);
                r = rec(root.right, false);
                money += l + r;
            } else {
                int l1 = 0, l2 = 0, r1 = 0, r2 = 0;
                l1 = rec(root.left, true);
                l2 = rec(root.left, false);
                r1 = rec(root.right, true);
                r2 = rec(root.right, false);
                money += Math.max(l1, l2) + Math.max(r1, r2);
            }
        }
        return money;
    }
}