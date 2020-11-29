class Solution {
    boolean[] visited;
    int count = 0;
    int length = 0;
    int[] arr;
    public boolean canReach(int[] arr, int start) {
        length = arr.length;
        visited = new boolean[length];
        this.arr = Arrays.copyOf(arr, length);
        return bfs(start);
    }

    private boolean bfs(int index) {
        if (arr[index] == 0) {
            return true;
        }
        visited[index] = true;
        count++;
        if (count < length) {
            int r = index + arr[index];
            if (r < length && !visited[r]) {
                if (bfs(r)) {
                    return true;
                }
            }
            int l = index - arr[index];
            if (l >= 0 && !visited[l]) {
                if (bfs(l)) {
                    return true;
                }
            }
        }
        visited[index] = false;
        return false;
    }
}
