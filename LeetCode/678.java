class Solution {
    public boolean checkValidString(String s) {
        char[] cc = s.toCharArray();
        int low = 0;
        int high = 0;
        for (char c: cc) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == '*') {
                low--;
                high++;
            } else {
                low--;
                high--;
            }
            if (high < 0) {
                return false; // 这个判断要及时，不能把后面的一起算进来，前面不行了就 false
            }
            low = Math.max(0, low); // 这句话非常重要
        }
        return low == 0;
    }
}
