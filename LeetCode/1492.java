class Solution {
    public int kthFactor(int n, int k) {
        int count = 0;
        int terminal = (int)Math.sqrt(n);
        for (int i = 1; i <= terminal; i++) {
            if (n % i == 0) {
                count++;
            }
            if (count == k) {
                return i;
            }
        }
        if (terminal * terminal == n) {
            terminal--;
        }
        for (int i = terminal; i >= 1; i--) {
            if (n % i == 0) {
                count++;
            }
            if (count == k) {
                return n / i;
            }
        }
        return -1;
    }
}
