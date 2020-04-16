class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        ListNode curHead = null, curTail = null;
        ListNode start = head, end = head;
        while (end != null) {
            int i = 0;
            for (i = 0; i < k; i++) {
                if (end == null) {
                    break;
                }
                end = end.next;
            }
            if (i < k) {
                if (curHead == null) {
                    curHead = head;
                } else {
                    curTail.next = start;
                }
            } else {
                ListNode[] newList = reverse(start, end);
                ListNode newHead = newList[0];
                ListNode newTail = newList[1];
                if (curHead == null) {
                    curHead = newHead;
                    curTail = newTail; 
                } else {
                    curTail.next = newHead;
                    curTail = newTail;
                }
                curTail.next = null;
                start = end;
            }
        }
        return curHead;
    }

    private ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode[] newList = new ListNode[2];
        ListNode p = head, q = head.next, r = null;
        while (q!= null && q != tail) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        newList[0] = p;
        newList[1] = head;
        return newList;
    }
}