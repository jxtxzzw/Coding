ArrayList<Integer> list = new ArrayList<>();

public int kthSmallest(TreeNode root, int k) {
    go(root);
    return list.get(k - 1);
}

private void go(TreeNode root) {
    if (root != null) {
        go(root.left);
        list.add(root.val);
        go(root.right);
    }
}