    public int longestValidParentheses(String s) {
        int left = 0;
        int right = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (left > right){
                right++;
            } else {
                left = 0; right = 0; // 很重要，清零
            }
            if (left == right) {
                ans = Math.max(ans, left + right);
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                right++;
            } else if (right > left){
                left++;
            } else {
                left = 0; right = 0; // 很重要，清零
            }
            if (left == right) {
                ans = Math.max(ans, left + right);
            }
        }
        return ans;
    }
