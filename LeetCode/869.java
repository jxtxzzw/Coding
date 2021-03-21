class Solution {
    public boolean reorderedPowerOf2(int N) {
        
        int[] digits = new int[10];
        while (N > 0) {
            digits[N % 10]++;
            N /= 10;
        }
        
        for (int i = 0; i <= 31; i++) {
            int po2 = (int)Math.pow(2, i);
            int[] possible = new int[10];
            while (po2 > 0) {
                possible[po2 % 10]++;
                po2 /= 10;
            }
            boolean same = true;
            for (int j =0; j < 10; j++) {
                if (possible[j] != digits[j]) {
                    same = false;
                    break;
                }
            }
            if (same) {
                return true;
            }
        }
        return false;
    }
}
