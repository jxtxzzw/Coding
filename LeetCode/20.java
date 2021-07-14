public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
        if (stack.isEmpty()) {
            stack.push(c);
        } else if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
        } else {
            char top = stack.peek();
            if (c == ')' && top == '(') {
                stack.pop();
            } else if (c == '}' && top == '{') {
                stack.pop();
            } else if (c == ']' && top == '[') {
                stack.pop();
            } else {
                break;
            }
        }
    }
    return stack.isEmpty();
}