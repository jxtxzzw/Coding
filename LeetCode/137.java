class Solution {
    public int singleNumber(int[] nums) {
        final int INT_SIZE = 32;
        int[] bits = new int[INT_SIZE];
        for (int num: nums) {
            for (int i = 0; i < INT_SIZE; i++) {
                bits[i] += (num >>> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < INT_SIZE; i++) {
            ans += ((bits[i] % 3) << i);
        }
        return ans;
    }
}
