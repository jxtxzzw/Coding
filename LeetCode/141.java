public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && slow != null) {
            if (fast == slow) {
                return true;
            }
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                fast = null;
            }
            slow = slow.next;
        }
        return false;
        
    }
}
