
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        LinkedList<Double> ans = new LinkedList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        LinkedList<Integer> d = new LinkedList<>();
        
        if (root == null) {
            return ans;
        }
        
        int prev = 1;
        double sum = 0;
        int count = 0;
        q.add(root);
        d.add(1);
        
        while (!q.isEmpty()) {
            TreeNode node = q.pollFirst();
            int depth = d.pollFirst();
            if (depth != prev) {
                prev = depth;
                ans.add(1.0 * sum / count);
                sum = 0;
                count = 0;
            }
            sum += node.val;
            count += 1;
            if (node.left != null) {
                q.add(node.left);
                d.add(depth + 1);
            }
            if (node.right != null) {
                q.add(node.right);
                d.add(depth + 1);
            }
        }
        ans.add(1.0 * sum / count);
        
        return ans;
        
    }
}
