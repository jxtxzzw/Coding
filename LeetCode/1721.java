class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        
        if (2 * k - 1 == len) {
            return head;
        }
        
        if (k > len / 2) {
            k = len + 1 - k;
        }
        
        ListNode prevK = null, revPrevK = null;

        int count = 1;
        cur = head;
        while (cur != null) {
            if (count == k - 1) {
                prevK = cur;
            }
            if (count == len - k) {
                revPrevK = cur;
            }
            count++;
            cur = cur.next;
        }
        
        if (k == 1) {
            ListNode K = head;
            head = revPrevK.next;
            revPrevK.next = K;
            head.next = K.next;
            K.next = null;
        } else {
            ListNode K = prevK.next;
            prevK.next = revPrevK.next;
            revPrevK.next = K;
            ListNode revKNext = prevK.next.next;
            prevK.next.next = K.next;
            K.next = revKNext;
        }
        
        
        
        
        return head;
        
    }
}
