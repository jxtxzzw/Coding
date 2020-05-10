class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] in = new int[N + 1];
        int[] out = new int[N + 1];
        for (int[] t : trust) {
            in[t[1]]++;
            out[t[0]]++;
        }
        for (int i = 1; i <= N; i++) {
            if (in[i] == N - 1 && out[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
