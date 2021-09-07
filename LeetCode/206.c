/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */


struct ListNode* reverseList(struct ListNode* head){
    // no effect if q is NULL or empty
    if (head == NULL) {
        return head;
    }
    struct ListNode* tail = head;
    // now the queue size must >= 1
    struct ListNode* prev = head;
    struct ListNode* cursor = prev->next;
    // go through the linked list and reverse it using 3 ptr, in O(n)
    while (cursor != NULL) {
	    struct ListNode* pioneer = cursor->next;
        cursor->next = prev;
        head = cursor;
        prev = cursor;
        cursor = pioneer;
    }
    // note that reversed tail should set its next as termination
    tail->next = NULL;
    return head;

}
