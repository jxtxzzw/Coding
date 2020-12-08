// N*2 的复杂度太高，要用空间换时间
// 只要记录 60 的余数，然后配对
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] r = new int[60];
        for (int i = 0; i < time.length; i++) {
            r[time[i] % 60]++;
        }
        int ans = 0;
        for (int i = 0; i <= 30; i++) {
            if (i == 0 || i == 30) {
                if (r[i] > 0) {
                    ans += r[i] * (r[i] - 1) / 2;
                }
            } else {
                ans += r[i] * r[60 - i];
            }
        }
        return ans;
    }
}
