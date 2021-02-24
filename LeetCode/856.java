class Solution {
    public int scoreOfParentheses(String S) {
        int count = 0;
        int score = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (c == ')') {
                if (S.charAt(i - 1) == '(') {
                    // 如果到了这个位置，就是一个 ()，要算分数
                    // 几分呢，它外面套了几层括号，它就是 2^x 分数（没套括号就是 1 分）
                    score += Math.pow(2, count);
                }
                // 不是所有 ) 都需要算分数的，因为只有 () 是 1 分
            }
        }
        return score;
    }
    
}
