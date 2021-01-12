class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode cursor = null;
        int carrier = 0;
        while (l1 != null || l2 != null) {
            int v1 = 0, v2 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }
            int val = v1 + v2 + carrier;
            carrier =  val / 10;
            ListNode node = new ListNode(val % 10);
            if (head == null) {
                head = node;
            } else {
                cursor.next = node;
            }
            cursor = node;
        }
        if (carrier == 1) {
            cursor.next = new ListNode(1);
        }
        return head;
    }
}
