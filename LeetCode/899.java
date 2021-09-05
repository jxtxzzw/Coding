class Solution {
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            // 只能一个个往后，暴力找到最小的
            String answer = s;
            for (int i = 1; i < s.length(); i++) {
                String candidate = s.substring(i) + s.substring(0, i);
                if (candidate.compareTo(answer) < 0) {
                    answer = candidate;
                }
            }
            return answer;
        } else {
            // k >= 2 意味着可以完全重组，这个观察点是最难的，要看出来
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }
}
