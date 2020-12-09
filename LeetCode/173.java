// 受控递归
// 因为只能用 O(h)，所以不能中序遍历为数组
// 而递归的话不能很方便地进行 next 判断，所以用栈模拟
class BSTIterator {
    private Stack<TreeNode> s = new Stack<>();

    public BSTIterator(TreeNode root) {
        go(root);
    }
    
    private void go(TreeNode node) {
        s.push(node);
        if (node.left != null) {
            go(node.left);
        }
    }
    
    public int next() {
        int v = 0;
        if (!s.isEmpty()) {
            TreeNode node = s.pop();
            v = node.val;
            if (node.right != null) {
                go(node.right);
            }
        }
        return v;
    }
    
    public boolean hasNext() {
        return !s.isEmpty();
    }
}
