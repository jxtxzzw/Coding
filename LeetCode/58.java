    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
            if (i < 0) {
                return 0;
            }
        }
        int ans = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            ans++;
            i--;
        }
        return ans;
    }