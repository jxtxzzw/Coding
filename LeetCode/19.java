class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;
        // 先拉开 n 的差距
        for (int i = 0; i < n; i++) {
            right = right.next;
            if (right == null) {
                return head.next;
            }
        }
        // 一起走，走到 right 到终点，left 就是倒数第 n 个
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return head;
    }
}