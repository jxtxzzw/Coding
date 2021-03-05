// 双指针
// 如果能倒着走的话，走到分岔口，cursorA != cursorB 就可以获得答案
// 但是没有 prev 指针，那就要先遍历一次，计算差值，让长的先走这么多，然后一起走
// 注意特判不相交的情况
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode cursorA = headA;
        ListNode cursorB = headB;
        while (cursorA != null) {
            lenA++;
            cursorA = cursorA.next;
        }
        while (cursorB != null) {
            lenB++;
            cursorB = cursorB.next;
        }
        int diff = Math.abs(lenA - lenB);
        if (lenA > lenB) {
            for (int i = 0; i < diff; i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 0; i < diff; i++) {
                headB = headB.next;
            }
        }
        cursorA = headA;
        cursorB = headB;
        while (cursorA != null && cursorB != null) {
            if (cursorA == cursorB) {
                return cursorA;
            }
            cursorA = cursorA.next;
            cursorB = cursorB.next;
        }
        return null;
    }
}
