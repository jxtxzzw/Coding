public ListNode reverseBetween(ListNode head, int left, int right) {

    // 虚拟头指针避免复杂的特殊情况判断
    ListNode dummy = new ListNode();
    dummy.next = head;

    ListNode in = dummy;
    for (int i = 0; i < left - 1; i++) {
        in = in.next;
    }
    ListNode out = in.next;

    // 快速遍历的核心：插入
    for (int i = 0; i < right - left; i++) {
        ListNode candidate = out.next;
        out.next = candidate.next;
        candidate.next = in.next;
        in.next = candidate;
    }

    return dummy.next;

}