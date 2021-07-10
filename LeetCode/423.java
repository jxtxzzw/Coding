class Solution {
    public String originalDigits(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        int[] nums = new int[10];
        // zero
        nums[0] += counts['z' - 'a'];
        counts['e' - 'a'] -= nums[0];
        counts['r' - 'a'] -= nums[0];
        counts['o' - 'a'] -= nums[0];
        // two
        nums[2] += counts['w' - 'a'];
        counts['t' - 'a'] -= nums[2];
        counts['o' - 'a'] -= nums[2];
        // six
        nums[6] += counts['x' - 'a'];
        counts['s' - 'a'] -= nums[6];
        counts['i' - 'a'] -= nums[6];
        // seven
        nums[7] += counts['s' - 'a'];
        counts['e' - 'a'] -= 2 * nums[7];
        counts['v' - 'a'] -= nums[7];
        counts['n' - 'a'] -= nums[7];
        // five
        nums[5] += counts['v' - 'a'];
        counts['f' - 'a'] -= nums[5];
        counts['i' - 'a'] -= nums[5];
        counts['e' - 'a'] -= nums[5];
        // eight
        nums[8] += counts['g' - 'a'];
        counts['e' - 'a'] -= nums[8];
        counts['i' - 'a'] -= nums[8];
        counts['h' - 'a'] -= nums[8];
        counts['t' - 'a'] -= nums[8];
        // three
        nums[3] += counts['h' - 'a'];
        counts['t' - 'a'] -= nums[3];
        counts['r' - 'a'] -= nums[3];
        counts['e' - 'a'] -= 2 * nums[3];
        // four
        nums[4] += counts['f' - 'a'];
        counts['o' - 'a'] -= nums[4];
        counts['u' - 'a'] -= nums[4];
        counts['r' - 'a'] -= nums[4];
        // one
        nums[1] += counts['o' - 'a'];
        counts['n' - 'a'] -= nums[1];
        counts['e' - 'a'] -= nums[1];
        // nine
        nums[9] += counts['i' - 'a'];
        counts['n' - 'a'] -= 2 * nums[9];
        counts['e' - 'a'] -= nums[9];
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            while (nums[i] != 0) {
                ans.append(i);
                nums[i]--;
            }
        }
        return ans.toString();
    }
}