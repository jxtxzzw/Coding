// 树，就递归地做
// 做完，再考虑优化
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 目标子树一定在更深的子树
        if (depth(root.left) > depth(root.right)) {
            return subtreeWithAllDeepest(root.left);
        } else if (depth(root.left) < depth(root.right)) {
            return subtreeWithAllDeepest(root.right);
        } else {
            // 左右相等就是答案
            return root;
        }
    }
    
    private int depth(TreeNode node) {
        // 有很多重复计算，可以考虑用记忆化搜索，如果算过，就 O(1) 查表
        if (node == null) {
            return 0;
        }
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }
}
