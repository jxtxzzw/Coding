class Solution {
    int maxIndex = 0;
    LinkedList<Integer> deq = new LinkedList<>();
    int k;
    int[] nums;
    void maintainDequeue(int i) {
        // 对于已经不在窗口范围内的 i，移除
        while (!deq.isEmpty() && deq.getFirst() <= i - k ) {
            deq.pollFirst();
        }
        // 遍历队列中的元素，如果队列中元素的值比当前值还要小，则无论如何滑动窗口，那个值不可能成为最大值，移除
        while (!deq.isEmpty() && nums[deq.getLast()]  < nums[i]) {
            deq.pollLast();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        this.nums = nums;
        this.k = k;
        ArrayList<Integer> ans = new ArrayList<>();
        // 遍历前 k 个元素，初始化双端队列，并记录最大下标
        for (int i = 0; i < k; i++) {
            maintainDequeue(i);
            deq.addLast(i);
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
        ans.add(nums[maxIndex]);
        // 至此，双端队列已经完成初始化，maxIndex 之后不会用到了
        // 之后要么 getFirst 的下标超过了窗口范围被移除，要么 getLast 比当前元素小被移除
        // 因此始终判断 getFirst 就是每一次窗口中最大元素的位置
        for (int i = k; i < nums.length; i++) {
            maintainDequeue(i);
            deq.addLast(i);
            assert !deq.isEmpty();
            ans.add(nums[deq.peekFirst()]);
        }
        int[] a = new int[nums.length - k + 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = ans.get(i);
        }
        return a;
    }
}
