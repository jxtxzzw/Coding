class Solution {
    public boolean hasAllCodes(String s, int k) {
        if (s.length() < k) {
            return false;
        }
        int l = 0;
        int r = k;
        int num = 0;
        for (int i = l; i < r; i++) {
            num = num * 2 + (s.charAt(i) - '0');
        }
        
        // 核心1：用哈希表记录是否存在
        boolean[] e = new boolean[(int)Math.pow(2, k)];
        e[num] = true;
        
        // 核心2：用滑动窗口避免对二进制转十进制的遍历，用 O(1) 的时间完成（掐头去尾计算差值）
        while (r < s.length()) {
            num -= (s.charAt(l) - '0') * Math.pow(2, k - 1);
            num *= 2;
            num += (s.charAt(r) - '0');
            l++;
            r++;
            e[num] = true;
        }
        
        for (int i = 0; i < e.length; i++) {
            if (!e[i]) {
                return false;
            }
        }
        return true;
    }
}