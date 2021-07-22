class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        boolean ans = true;
        while (i < bits.length) {
            if (bits[i] == 0) {
                i++;
                ans = true;
            }
            else {
                i += 2;
                ans = false;
            }
        }
        return ans;
    }
}
