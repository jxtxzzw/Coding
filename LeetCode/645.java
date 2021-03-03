class Solution {
    public int[] findErrorNums(int[] nums) {
        // 位运算是很经典的算法，使用 xor 可以得到 dup^mis 的结果，因为 dup 会出现 3 次而 mis 只出现 1 次，其余数字都出现 2 次
        int xor = 0;
        for (int i = 1; i <= nums.length; i++) {
            xor ^= i;
            xor ^= nums[i - 1];
        }
        System.out.print("xor = " + xor);
        // 现在我们知道了 dup^mis，要分别求出 dup 和 mis 是多少
        // 记 dup^mis 从右数第 r 位为首次出现 1，这样说明这一位上 dup 和 mis 的值不同
        int r = 0;
        while (((xor >> r) & 1) == 0) {
            r++;
        }
        System.out.println("xor = " + xor + ", r = " + r);
        // 根据这一位不同将数字分为 2 组，一组就会是出现 3 次的 dup 而另一个就会是 mis
        int[] xors = new int[]{0, 0};
        for (int i = 1; i <= nums.length; i++) {
            xors[(i >> r) & 1] ^= i;
            xors[(nums[i - 1] >> r) & 1] ^= nums[i - 1];
        }
        // xors 中一个是 dup 另一个是 mis，遍历原数组知道谁是谁
        for (int i = 0; i < nums.length; i++) {
            if (xors[0] == nums[i]) {
                return new int[]{xors[0], xors[1]};
            }
        }
        return new int[]{xors[1], xors[0]};
        
        
    }
}
