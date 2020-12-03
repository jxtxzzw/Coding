// 中序有序，但是不要占用额外的空间再重新构造，在遍历的过程中就完成左右子树的调整。
// 递归找到最左的 head，然后在递归返回时调整左右子树，主意不要有环，最后一个结点可能需要在 return 前把左子树设置为 null。 
class Solution {
    
    private TreeNode head = null;
    private TreeNode cursor = null;
    
    public TreeNode increasingBST(TreeNode root) {
        if (root != null) {
            go(root);
            cursor.left = null; // 最后别忘了去掉这个 cycle
        }
        return head;
    }
    
    private void go(TreeNode root) {
        if (root.left != null) {
            go(root.left);
        }
        if (head == null) {
            head = root;
        } else {
            cursor.right = root;
            cursor.left = null;
        }
        cursor = root;
        if (root.right != null) {
            go(root.right);
        }
    }
}
