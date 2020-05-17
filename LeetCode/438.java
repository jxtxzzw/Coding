class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) {
            return ans; // 注意边界情况
        }
        int[] target = new int[26];
        for (int i = 0; i < p.length(); i++) {
            target[p.charAt(i) - 'a']++;
        }
        int[] count = new int[26];
        char[] ss = s.toCharArray();
        int l = 0;
        int r = p.length();
        for (int i = 0; i < r; i++) {
            count[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(count, target)) {
            ans.add(l);
        }
        while (r < s.length()) {
            count[s.charAt(l++) - 'a']--;
            count[s.charAt(r++) - 'a']++;
            if (Arrays.equals(count, target)) {
                ans.add(l);
            }
        }
        return ans;
    }

}
