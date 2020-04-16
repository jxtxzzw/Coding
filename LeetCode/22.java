class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        }
        for (int i = 0; i < n; i++) {
            for (String left : generateParenthesis(i)) {
                for (String right : generateParenthesis(n - 1 - i)) {
                    ans.add("(" + left + ")" + right);
                }
            }
        }
        return ans;
    }
}