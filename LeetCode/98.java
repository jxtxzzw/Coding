class Solution {

    ArrayList<Integer> list = new ArrayList<>();

    public boolean isValidBST(TreeNode root) {
        mid(root);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void mid(TreeNode node) {
        if (node == null) {
            return;
        }
        mid(node.left);
        list.add(node.val);
        mid(node.right);
    }
}
