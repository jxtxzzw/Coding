/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    private int length = 0;
    private ListNode head;
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        ListNode cursor = head;
        while (cursor != null) {
            length++;
            cursor = cursor.next;
        }    
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int r = (int)(Math.random() * length);
        ListNode cursor = head;
        while (r-- != 0) {
            cursor = cursor.next;
        }
        return cursor.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
