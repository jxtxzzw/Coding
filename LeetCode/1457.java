// Note that the node values of a path form a palindrome if at most one digit has an odd frequency (parity).
// Use a Depth First Search (DFS) keeping the frequency (parity) of the digits. Once you are in a leaf node check if at most one digit has an odd frequency (parity).
class Solution {
    
    private int[] count = new int[10];
    private int ans = 0;
    
    public int pseudoPalindromicPaths (TreeNode root) {
        if (root == null) {
            return 0;
        }
        go(root);
        return ans;
    }
    
    private void go(TreeNode root) {
        count[root.val] += 1;
        if (root.left == null && root.right == null) {
            System.out.println(Arrays.toString(count));
            int odd = 0;
            for (int i = 0; i < 10; i++) {
                if (count[i] % 2 != 0) {
                    odd++;
                }
            }
            if (odd <= 1) {
                ans++;
            }
        }
        if (root.left != null) {
            go(root.left);        
        }
        if (root.right != null) {
            go(root.right);            
        }
        count[root.val] -= 1;
    }
}
