// 坑1；ha22 不是把 ha 重复 22 次，而是 (ha2)2，即 hahahaha
class Solution {
    public String decodeAtIndex(String S, int K) {
        System.out.println(S + " " + K);
        // 计数需要 long
        long count = 0;
        int i = 0;
        while (i < S.length()) {
            char c = S.charAt(i);
            int d = 1;
            if (c >= '2' && c <= '9') {
                d = c - '0';
                count *= d;
            } else {
                count++;
            }
            System.out.println("i=" + i + ", c=" + S.charAt(i) + ", count=" + count +", K=" + K);
            if (count >= K) {
                // 关键：这时候我们知道 K 在已经出现的这段字符串中，要用取余数的方法，将重复部分去掉
                count /= d;
                K %= count;
                System.out.println("i=" + i + ", c=" + S.charAt(i) + ", count=" + count +", K=" + K);
                if (K == 0) {
                    // 余数为 0 的情况
                    while (S.charAt(i) >= '2' && S.charAt(i) <= '9') {
                        i--;
                    }
                    return String.valueOf(S.charAt(i));  
                } else {
                    return decodeAtIndex(S.substring(0, i), K);
                }
            }
            i++;
        }
        // Should never goes here
        return null;
    }
}
