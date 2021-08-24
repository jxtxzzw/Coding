class Solution {

    
    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = strs[0].length();
        for (String s : strs) {
            minLength = Math.min(minLength, s.length());
        }

        int l = 0;
        int r = minLength;

        while (l <= r) {
            int mid = (l + r) / 2;
            String candidate = strs[0].substring(0, mid);
            if (bs(candidate, strs)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return strs[0].substring(0, (l + r) / 2);

    }

    private boolean bs(String candidate, String[] strs) {
        System.out.println(candidate);

        for (String s : strs) {
            if (!s.startsWith(candidate)) {
                return false;
            }
        }
        return true;
    }
}
