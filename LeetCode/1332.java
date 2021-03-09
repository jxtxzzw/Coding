// 这道题很 tricky，因为允许删掉的是一个子序列，由由于只包含 a 和 b，这意味着最多 2 次就可以删完
// 无论如何妖孽的排列，第一次取出所有 a 的子序列，是回文的，删完，就只剩下一排 b，所以最多 2 次
// 如果原来是空就是 0 次，如果原来就是回文就是 1 次
class Solution {
    public int removePalindromeSub(String s) {
        if (s.equals("")) {
            return 0;
        }
        StringBuffer sb = new StringBuffer(s);
        return sb.reverse().toString().equals(s) ? 1 : 2;
    }
}
