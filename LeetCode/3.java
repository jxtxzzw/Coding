class Solution {

    HashSet<Character> set = new HashSet<>();

    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        int n = s.length();
        int ans = 0;
        if (n == 0) {
            return ans;
        } else {
            ans = 1;
        }
        int left = 0, right = 1;
        set.clear();
        set.add(c[left]);
        while (right < n) {
            if (set.contains(c[right])) {
                set.remove(c[left]);
                left++;
            } else {
                set.add(c[right]);
                ans = Math.max(ans, right - left + 1);
                right++;
            }
        }
        return ans;
    }
}
