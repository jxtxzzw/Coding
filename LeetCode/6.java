class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] sbs = new StringBuilder[numRows];
        int i = 0;
        boolean down = true;
        for (char c : s.toCharArray()) {
            if (sbs[i] == null) {
                sbs[i] = new StringBuilder();
            }
            sbs[i].append(c);
            if (down) {
                i++;
                if (i == numRows) {
                    down = false;
                    i -= 2;
                }
            } else {
                i--;
                if (i == -1) {
                    down = true;
                    i += 2;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder x : sbs) {
            if (x == null) {
                continue;
            }
            sb.append(x);
        }
        return sb.toString();
    }
}