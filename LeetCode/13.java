class Solution {
    public int romanToInt(String s) {
        int ans = 0;
        int i = 0;
        while (i < s.length()) {
            int p = 0;
            if (s.charAt(i) == 'I') {
                p = 1;
                if (i < s.length() - 1) {
                    if (s.charAt(i + 1) == 'V') {
                        p = 4;
                        i++;
                    } else if (s.charAt(i + 1) == 'X') {
                        p = 9;
                        i++;
                    }
                }
            } else if (s.charAt(i) == 'V') {
                p = 5;
            } else if (s.charAt(i) == 'X') {
                p = 10;
                if (i < s.length() - 1) {
                    if (s.charAt(i + 1) == 'L') {
                        p = 40;
                        i++;
                    } else if (s.charAt(i + 1) == 'C') {
                        p = 90;
                        i++;
                    }
                }
            } else if (s.charAt(i) == 'L') {
                p = 50;
            } else if (s.charAt(i) == 'C') {
                p = 100;
                if (i < s.length() - 1) {
                    if (s.charAt(i + 1) == 'D') {
                        p = 400;
                        i++;
                    } else if (s.charAt(i + 1) == 'M') {
                        p = 900;
                        i++;
                    }
                }
            } else if (s.charAt(i) == 'D') {
                p = 500;
            } else if (s.charAt(i) == 'M') {
                p = 1000;
            }
            ans += p;
            i++;
        }
        return ans;
    }
}