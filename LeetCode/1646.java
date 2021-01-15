class Solution {
    public int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }
        int[] a = new int[n + 1];
        a[0] = 0;
        a[1] = 1;
        int max = 1;
        for (int i = 1; i <= n / 2; i++) {
            if (i * 2 <= n) {
                a[i * 2] = a[i];
            }
            if (i * 2 + 1 <= n) {
                a[i * 2 + 1] = a[i] + a[i + 1];
                if (a[i * 2 + 1] > max) {
                    max = a[i * 2 + 1];
                }
            }
        }
        return max;
    }
}
