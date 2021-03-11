class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] cache = new int[amount + 1];
        cache[0] = 0;
        for (int i = 1; i < cache.length; i++) {
            cache[i] = Integer.MAX_VALUE;
        }
        
        for (int c : coins) {
            if (c >= cache.length) {
                continue;
            }
            cache[c] = 1;
        }
        
        for (int i = 1; i < cache.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i - c < 0) {
                    continue;
                }
                min = Math.min(cache[i - c], min);
            }
            if (min == Integer.MAX_VALUE) {
                continue;
            }
            cache[i] = Math.min(cache[i], min + 1);
        }
        return cache[amount] == Integer.MAX_VALUE ? -1 : cache[amount];
    }
}
