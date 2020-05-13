class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            // k 还大于 0，则有挑三拣四的余地
            while (k > 0 && !stack.isEmpty() && c < stack.peek()) {
                stack.pop();
                k--;
            }
            if (stack.isEmpty()) {
                if (c != '0') {
                    // 避免前导零
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }
        return ans.length() == 0 ? "0" : ans.reverse().toString();

    }
}
