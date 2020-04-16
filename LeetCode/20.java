class Solution {
    public boolean isValid(String s) {
        final HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (map.containsValue(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && stack.peek().equals(map.get(c))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }
}