class Solution {
    public boolean isValidSequence(TreeNode root, int[] arr) {
        int n = arr.length;
        if (root == null) {
            return false;
        } else {
            if (arr[0] != root.val) {
                return false;
            } else if (n == 1) {
                return (root.left == null && root.right == null);
            } else {
                int[] sub = Arrays.copyOfRange(arr, 1, arr.length);
                return isValidSequence(root.left, sub)  || isValidSequence(root.right, sub);
            }
        }
    }
}
