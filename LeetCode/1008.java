class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return build(preorder[0], Arrays.copyOfRange(preorder, 1, preorder.length));
    }

    public TreeNode build(int val, int[] rest) {
        TreeNode left = null, right = null;
        if (rest.length > 0) {
            int index = Math.min(-(Arrays.binarySearch(rest, val) + 1), rest.length);
            int[] l = Arrays.copyOfRange(rest, 0, index);
            int[] r = Arrays.copyOfRange(rest, index, rest.length);
            left = bstFromPreorder(l);
            right = bstFromPreorder(r);
        }
        return new TreeNode(val, left, right);
    }
}
