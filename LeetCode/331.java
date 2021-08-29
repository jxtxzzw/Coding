class Solution {
    /*
    这里有一个很巧妙的做法，那就是判断一个前序遍历是不是合法，就是看允许出现多少次 #
    只有一个结点非空，它才可能出现两个孩子结点，即 possible null，即 tolerance
    注意 tolerance 在任何时候小于 0 就直接 false
     */
    public boolean isValidSerialization(String preorder) {
        int tolerance = 0;
        if (preorder.charAt(0) != '#') {
            tolerance += 2;
        }
        for (int i = 1; i < preorder.length();) {
            if (preorder.charAt(i) == '#') {
                if (tolerance <= 0) {
                    return false;
                } else {
                    tolerance--;
                }
                i++;
            } else if (preorder.charAt(i) == ',') {
                i++;
            } else {
                if (tolerance <= 0) {
                    return false;
                } else {
                    tolerance--;
                }
                tolerance += 2;
                while (i < preorder.length() && Character.isDigit(preorder.charAt(i))) {
                    i++;
                }
            }
        }
        return tolerance == 0;
    }
}
