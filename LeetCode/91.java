// 分支比较多，想清楚每种情况要加，还是不要加
class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        int ans = (s.charAt(0) == '0') ? 0 : 1;
        // 本质上是 dp，dp[i] 通过 dp[i - 1] 和 dp[i - 1] 获得
        int a = ans, b = ans; // 用变量是想要避免 dp 的空间复杂度
        
        for (int i = 1; i < len; i++) {
            char prev = s.charAt(i - 1);
            char cur = s.charAt(i);
            
            if (cur == '0') {
                // 0 无法组成合适的数字，直接失败
                if (prev != '1' && prev != '2') {
                    return 0;
                }
                // 10、20 都不增加 ans 的数量
                ans = a;
                b = a; // 这句非常重要
                continue;
            }
            
            if (prev == '1') {
                ans = a + b;
            } else if (prev == '2') {
                if (cur >= '1' && cur <= '6') {
                    ans = a + b;
                }
            }
            // 有点像 Fibonacci 的迭代，可以省掉 dp 数组的开销
            a = b;
            b = ans;
            // System.out.println(a + ", " + b);
        }
        
        return ans;
    }
}
