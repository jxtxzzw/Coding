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


/*

class Solution {
    private Stack<TreeNode> stack = new Stack<>();

    public int kthSmallest(TreeNode root, int k) {
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            k--;
            root = stack.pop();
            if (k == 0) {
                return root.val;
            } else {
                root = root.right;
            }
        }
    }
}

*/
