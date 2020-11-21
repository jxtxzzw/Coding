// 方法1：数位 DP
// 方法2：数学推导（进制转换）

// 方法3：数学推导 + 贪心
class Solution {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        int len = digits.length;
        int ans = 0;
        // 首先求出这是几位数，那么小于它的位数，可以取遍所有的可能
        int m = (int) Math.log10(n);
        for (int i = 1; i <= m; i++) {
            ans += (int) Math.pow(len, i);
        }
        // 现在只剩下 M 位数了
        Arrays.sort(digits);

        while (m >= 0) {
            boolean breaked = false;
            // 最高位是 d
            int d = n / (int) Math.pow(10, m);
            n -= d * (int) Math.pow(10, m);
            for (String digit : digits) {
                // 尝试取出数组的某一位 t
                int t = Integer.parseInt(digit);
                if (t < d) {
                    // 如果小于，那么剩下 m 位随便放
                    ans += (int) Math.pow(len, m);
                } else if (t == d) {
                    if (m == 0) {
                        ans++;
                    }                    // 固定了，最高位只能填这个数了
                    m--;
                    // 因为排序过，后面的都是大于的，不能放
                    breaked = true;
                    break;
                }
            }
            // 如果是遍历完 for 循环的，那就直接结束了
            if (!breaked) {
                break;
            }
        }
        return ans;
    }
}
