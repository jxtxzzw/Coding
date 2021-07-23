/* 通常，涉及连续子数组问题的时候，我们使用前缀和来解决它们。 */
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        // 计算前缀和
        int[] P = new int[A.length + 1];
        for (int i = 0; i < A.length; i++)
            P[i + 1] = P[i] + A[i];
        // 如果 P[i] 和 P[j] 模 K 同余，说明 A[i] 到 A[j] 之间的数的和是 K 的倍数
        // C[i] 记录有多少个 P 模 K 的余数是相同的，那么这些 P 两两组合成 P[i] 和 P[j]，就是答案
        int[] C = new int[K];
        for (int x : P)
            C[(x % K + K) % K]++; // 由于和可能为负，所以 x % K 要计算出 (x % K + K ) % K
        int ans = 0;
        // 如果 C[i] = x，则两两组合有 x * (x - 1) / 2 种方案
        for (int x : C)
            ans += x * (x - 1) / 2;
        return ans;
        
    }
}

