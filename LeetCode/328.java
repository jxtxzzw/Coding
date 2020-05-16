class Solution {
    public ListNode oddEvenList(ListNode head) {
        ListNode oddHead = null;
        ListNode evenHead = null;
        ListNode oddCursor = null;
        ListNode evenCursor = null;
        while (head != null) {
            if (oddHead == null) {
                oddHead = oddCursor = head;
            } else {
                oddCursor.next = head;
                oddCursor = oddCursor.next;
            }
            head = head.next;
            if (head != null) {
                if (evenHead == null) {
                    evenHead = evenCursor = head;
                } else {
                    evenCursor.next = head;
                    evenCursor = evenCursor.next;
                }
                head = head.next;
            }
        }
        if (oddHead != null) {
            oddCursor.next = evenHead;
        }
        if (evenCursor != null) {
            evenCursor.next = null;
        }
        return oddHead;
    }
}
