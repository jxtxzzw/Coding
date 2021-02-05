class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<String>();
        String[] dirs = path.split("/");
        for (String dir : dirs) {
            switch (dir) {
                case "":
                case ".":
                    break;
                case "..":
                    if (!stack.empty()) {
                        stack.pop();
                    }
                    break;
                default:
                    stack.push(dir);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append("/");
            sb.append(s);
        }
        
        if (sb.length() == 0) {
            sb.append("/");
        }
        
        return sb.toString();
    }
}
