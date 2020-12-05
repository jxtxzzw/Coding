// 就是贪心，但是不需要模拟，直接数学计算
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 需要种 n 朵花，就需要 2*n 个位置，因为不能相邻
        // 所以只需要判断数组中 0 的个数是不是满足条件
        int candidates = 0;
        int i = 0;
        while (i < flowerbed.length) {
            int zeros = 0;
            // 多少个 0
            while (i < flowerbed.length && flowerbed[i] == 0) {
                zeros++;
                i++;
            }
            // 1 边上的 0 不能用，要减掉
            if (i < flowerbed.length) {
                assert flowerbed[i] == 1;
                if (i > 0) {
                    zeros--;
                }
                i+=2;
            }
            if (zeros > 0) {
                // 计算可以种多少
                candidates += (int)Math.ceil(zeros / 2.0);
            }
        }
        
        return candidates >= n;
    }
}
