// 合并为 2 个数组，这样就只要 0 - x 查
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        // 预处理，然后 O(1) 查表
        HashMap<Integer, Integer> AB = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = A[i] + B[j];
                if (AB.containsKey(x)) {
                    AB.put(x, AB.get(x) + 1);
                } else {
                    AB.put(x, 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int y = C[i] + D[j];
                if (AB.containsKey(-y)) {
                    ans += AB.get(-y);
                }
            }
        }
        return ans;
    }
}
