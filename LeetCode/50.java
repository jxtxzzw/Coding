class Solution {
    public double myPow(double x, int n) {
        long N = (long) n; // 避免 -2147483648 取负号溢出
        return n == 0 ? 1 : n > 0 ? quickPow(x, N) : quickPow(1 / x, -N);
    }

    private double quickPow(double x, long n) {
        double ans = 1;
        while (n > 1) {
            if (n % 2 == 1) {
                ans *= x;
            }
            x *= x;
            n /= 2;
        }
        return ans * x;
    }
}
