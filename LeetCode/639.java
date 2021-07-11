class Solution {
    // 分支比较多，想清楚每种情况要加，还是不要加
    public int numDecodings(String s) {
        int MOD = 1000000007;

        int len = s.length();
        long ans = (s.charAt(0) == '0') ? 0 : (s.charAt(0) == '*') ? 9 : 1;
        // 本质上是 dp，dp[i] 通过 dp[i - 1] 和 dp[i - 2] 获得
        long a = 1, b = ans; // 用变量是想要避免 dp 的空间复杂度

        for (int i = 1; i < len; i++) {
            char prev = s.charAt(i - 1);
            char cur = s.charAt(i);

            if (cur == '0') {
                if (prev == '1' || prev == '2') {
                    // 10、20 都不增加 ans 的数量
                    ans = a;
                } else if (prev == '*') {
                    // 可以是 10 或者 20
                    ans = a * 2 % MOD;
                } else {
                    // 0 无法组成合适的数字，直接失败
                    return 0;
                }
            } else if (cur >= '1' && cur <= '6') {
                if (prev == '1' || prev == '2') {
                    ans = (a + b) % MOD;
                } else if (prev == '*') {
                    ans = (a * 2 % MOD + b) % MOD;
                } else {
                    ans = b;
                }
            } else if (cur >= '7' && cur <= '9') {
                if (prev == '1') {
                    ans = (a + b) % MOD;
                } else if (prev == '2') {
                    ans = b;
                } else if (prev == '*') {
                    ans = (a + b) % MOD;
                }
            } else if (cur == '*') {
                if (prev == '1') {
                    ans = (a * 9 % MOD + b * 9 % MOD) % MOD;
                } else if (prev == '2') {
                    ans = (a * 6 % MOD + b * 9 % MOD) % MOD;
                } else if (prev == '*'){
                    ans = (a * (9 + 6) % MOD + b * 9 % MOD) % MOD;
                } else {
                    ans = b * 9 % MOD;
                }
            }

            // 有点像 Fibonacci 的迭代，可以省掉 dp 数组的开销
            a = b;
            b = ans;
        }

        System.out.println(ans);
        return (int)(ans % MOD);
    }
}
