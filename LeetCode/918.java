class Solution {
    public int maxSubarraySumCircular(int[] A) {
        // 循环数组可以用 A + A 表示，可覆盖循环数组的任何一种情况
        int[] prefix = new int[A.length * 2];
        prefix[0] = A[0];
        for (int i = 1; i < prefix.length; i++) {
            // prefix 计算前缀和
            prefix[i] = prefix[i - 1] + A[i % A.length];
        }
        // 不能直接找到 prefix[] 的 max 和 min 然后 return max - min
        // 因为有额外的限制条件，1 <= j - i <= N，即确保长度小于 N
        // 之前的都是自己想到的，但是如何处理 1 <= j - i <= N 是看了题解的，需要复习
        LinkedList<Integer> list = new LinkedList<>();
        int ans = prefix[0];
        list.add(0);
        // 枚举每一个可能的 prefix[i] 作为那个 max，要去找一个 min
        for (int i = 1; i < prefix.length; i++) {
            // 如果范围大于 N 了，则出队队首元素，只在规定范围内找
            while (!list.isEmpty() && i - list.peekFirst() > A.length ) {
                list.pollFirst();
            }
            // 此时的第一个就一定是 min 的，那么计算 max - min，记录最大的 max - min
            if (!list.isEmpty()) {
                ans = Math.max(ans, prefix[i] - prefix[list.peekFirst()]);
            }
            // 由于 prefix[i] 是当前枚举的 max，所以所有比它大的都出队，只保留比它小的
            while (!list.isEmpty() && prefix[i] <= prefix[list.peekLast()]) {
                list.pollLast();
            }
            list.addLast(i);
        }
        return ans;
    }
}
