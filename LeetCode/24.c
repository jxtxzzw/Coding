struct ListNode* swapPairs(struct ListNode* head){
    struct ListNode* newHead = head;
    struct ListNode* cur = head;
    struct ListNode* prev = NULL;
    while (cur != NULL) {
        struct ListNode* next = cur->next;
        if (next != NULL) {
            cur->next = next->next;
            next->next = cur;
            if (prev != NULL) {
                prev->next = next;            
            } else {
                newHead = next;
            }
        }
        prev = cur;
        cur = cur->next;
    }
    return newHead;
    
}
