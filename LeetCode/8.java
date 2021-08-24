
class Solution {
    public int myAtoi(String s) {
        boolean negative = false;

        int cursor = 0;
        while (cursor < s.length() && s.charAt(cursor) == ' ') {
            cursor++;
        }
        if (cursor == s.length()) {
            return 0;
        }
        if (s.charAt(cursor) == '-') {
            negative = true;
            cursor++;
        } else if (s.charAt(cursor) == '+' ) {
            cursor++;
        } else if (!Character.isDigit(s.charAt(cursor))) {
            return 0;
        }

        long ans = 0;
        for (int i = cursor; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                ans = ans * 10 + (s.charAt(i) - '0');
                if (ans > 2147483647) {
                    break;
                }
            } else {
                break;
            }
        }

        int MIN = -2147483648;
        int MAX = 2147483647;

        if (negative) {
            if (-ans < MIN) {
                return MIN;
            } else {
                return (int)-ans;
            }
        } else {
            if (ans > MAX) {
                return MAX;
            } else {
                return (int)ans;
            }
        }
    }
}