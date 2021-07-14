class Solution {
    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        } else if (digits.length() == 1) {
            List<String> ans = new ArrayList<>();
            char[] cc = KEYS[digits.charAt(0) - '0'].toCharArray();
            for (char c : cc) {
                ans.add("" + c);
            }
            return ans;
        }
        List<String> ans = new ArrayList<>();
        List<String> prev = letterCombinations(digits.substring(0, digits.length() - 1));
        char[] cc = KEYS[digits.charAt(digits.length() - 1) - '0'].toCharArray();
        for (String s : prev) {
            for (char c : cc) {
                ans.add(s + c);
            }
        }
        return ans;
    }
}
