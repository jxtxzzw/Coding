class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        ListNode root = null, cursor = null;
        while (true) {
            int min = -1;
            for (int i = 0; i < n; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (min == -1 || lists[i].val < lists[min].val) {
                    min = i;
                }
            }
            if (min == -1) {
                break;
            }
            if (root == null) {
                root = lists[min];
                cursor = root;
            } else {
                cursor.next = lists[min];
                cursor = cursor.next;
            }
            lists[min] = lists[min].next;
        }
        return root;
    }
}
