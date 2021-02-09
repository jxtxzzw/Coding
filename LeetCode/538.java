// 树，就直接递归做，用循环和栈来模拟也是一样的 O(n) O(n)
// 但是存在一种做法是空间复杂度为 O(1) 的
class Solution {
    
     // 获取一个结点的直接后继结点
    private TreeNode getSuccessor(TreeNode node) {
        // 这个行为只在存在后继结点时会调用
        assert (node != null) && (node.right != null);
        TreeNode succ = node.right;
        // 不断找到最左结点，就是直接后继结点，判断 succ.left != node 是为了避免出现环
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }
    
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode cursor = root;
        
        while (cursor != null) {
            // 如果没有比这个结点更大的了，就可以更新 val
            if (cursor.right == null) {
                sum += cursor.val;
                cursor.val = sum;
                cursor = cursor.left; // 这是有可能通过捷径回去的
            } else {
                TreeNode succ = getSuccessor(cursor);
                if (succ.left == null) {
                    // 说明这是直接后继结点，因为直接后继结点的 left 为 null （否则就会存在比它小的结点，它就不是直接后继）
                    succ.left = cursor; // 指回 node，就是这个结点的直接前驱，我们在遍历完比它大的数字的时候，可以方便地返回
                    cursor = cursor.right; // 遍历 cursor 的右子树，这些数字是要加在 cursor 的 val 上的，注意不是 succ 的右子树
                } else {
                    // 直接后继的 left 不为 null，说明我们访问过它，修改过这个辅助的 left，这是通过捷径直接回到前驱
                    succ.left = null; // 还原
                    // 在遍历的过程中，右子树的各个结点（从大到小）的 val 都被更新过了，现在轮到这个了
                    sum += cursor.val;
                    cursor.val = sum;
                    cursor = cursor.left; // 通过捷径回去
                }
            }
        }
        
        return root;
    }
}