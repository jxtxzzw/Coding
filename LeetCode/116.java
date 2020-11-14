
// 因为只能使用常数空间，所以不能层次遍历（bfs），那么技巧就是每处理完一行，就横着走这一行并处理下一行，注意 null 的判断不要漏
class Solution {
    public Node connect(Node root) {
        Node previous = root;
        while (previous != null) {
            Node cursor = previous;
            while (cursor != null) {
                if (cursor.left != null) {
                    cursor.left.next = cursor.right;
                    // when cursor.left is null, cursor.right does not exist
                    if (cursor.next != null) {
                        cursor.right.next = cursor.next.left;
                    }
                }
                cursor = cursor.next;
            }
            previous = previous.left;
        }
        return root;
    }
}
