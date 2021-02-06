class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        
        if (root == null) {
            return list;
        }

        // 层次遍历
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int len = q.size();
            // 这一层有 len 个结点，遍历并添加它们的子节点（下一层结点）
            for (int i = 0; i < len; i++) {
                TreeNode cur = q.pollFirst();
                // 每一层只保留最右边的结点
                if (i == 0) {
                    list.add(cur.val);                
                }
                // 层次遍历，从右边优先添加
                if (cur.right != null) {
                    q.addLast(cur.right);
                }
                if (cur.left != null) {
                    q.addLast(cur.left);
                }
                
            }
            
        }
        return list;
        
    }
}
