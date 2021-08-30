class Solution {
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println(Arrays.toString(strs));
        int ans = -1;
        for (int i = 0; i < strs.length; i++) {
            boolean pass = true;
            for (int j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isSubSequence(strs[i], strs[j])) {
                    pass = false;
                    break;
                }
            }
            if (pass) {
                ans = Math.max(strs[i].length(), ans);
            }
        }
        return ans;
    }

    private boolean isSubSequence(String s, String t) {
        int c1 = 0, c2 = 0;
        while (c1 < s.length() && c2 < t.length()) {
            if (s.charAt(c1) == t.charAt(c2)) {
                c1++;
                c2++;
            } else {
                c2++;
            }
        }
        return c1 == s.length();
    }
}
