struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2){
    struct ListNode* head = (struct ListNode*)malloc(sizeof(struct ListNode));
    head->next = NULL;
    struct ListNode* p = head;
    while (l1 != NULL && l2 != NULL) {
        if (l1->val < l2->val) {
            p->next = l1;
            l1 = l1->next;
        } else {
            p->next = l2;
            l2 = l2->next;
        }
        p = p->next;
    }
    while (l1 != NULL) {
        p->next = l1;
        l1 = l1->next;
        p = p->next;
    }
    while (l2 != NULL) {
        p->next = l2;
        l2 = l2->next;
        p = p->next;
    }
    return head->next;
}
