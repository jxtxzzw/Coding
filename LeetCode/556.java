class Solution {
    public int nextGreaterElement(int n) {
        char[] c = String.valueOf(n).toCharArray();
        int len = c.length;
        // 从个位开始，找第一个能交换了变大的
        for (int i = len - 1; i > 0; i--) {
            if (c[i] > c[i - 1]) {
                // c[i - 1] 是我们要交换掉的数字
                int j = len - 1;
                // 找到第一个大于 c[i - 1] 的数字，交换
                while (j >= i) {
                    if (c[j] > c[i - 1]) {
                        break;
                    }
                    j--;
                }
                // 将 c[j] 与 c[i - 1] 交换，确保这一位是最小的
                char t = c[j];
                c[j] = c[i - 1];
                c[i - 1] = t;
                
                System.out.println(Arrays.toString(c));
                
                // 这时候需要确保这是最小的，所以 i 后面的数字升序排序
                // 可以用插入排序，将 i ~  len - 1 排序
                for (j = i; j < len; j++) {
                    char x = c[j];
                    int k = j - 1;
                    while (k >= i && x < c[k]) {
                        c[k + 1] = c[k];
                        k--;
                    }
                    c[k + 1] = x;
                    System.out.println(Arrays.toString(c));
                }
                
                
                // 这个就是答案
                long ans = Long.parseLong(new String(c));
                if (ans > Integer.MAX_VALUE) {
                    return -1;
                } else {
                    return (int)ans;
                }
            }
        }
        return -1;
    }
}
