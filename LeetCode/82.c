struct ListNode* deleteDuplicates(struct ListNode* head){
    struct ListNode* newHead = head;
    struct ListNode* cursor = head;
    struct ListNode* prev = NULL;
    while (cursor != NULL) {
        bool duplicated = false;
        while (cursor->next != NULL && cursor->val == cursor->next->val) {
            cursor = cursor->next;
            duplicated = true;
        }
        if (duplicated) {
            if (prev == NULL) {
                newHead = cursor->next;
            } else {        
                prev->next = cursor->next;
            }
        } else {
            if (prev == NULL) {
                newHead = cursor;
            } else {        
                prev->next = cursor;
            }
            prev = cursor;
        }
        cursor = cursor->next;
    }
    return newHead;
}
