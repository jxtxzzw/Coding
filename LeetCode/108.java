
class Solution {
    int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return sortedArrayToBST_R(0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST_R(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST_R(left, mid - 1);
        root.right = sortedArrayToBST_R(mid + 1, right);
        return root;
    }
}