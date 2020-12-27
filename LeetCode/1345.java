class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        
        // Hint 1: Build a graph of n nodes where nodes are the indices of the array and edges for node i are nodes i+1, i-1, j where arr[i] == arr[j].
        HashMap<Integer, LinkedList<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (g.containsKey(arr[i])) {
                g.get(arr[i]).addLast(i);
            } else {
                LinkedList<Integer> l = new LinkedList<>();
                l.addLast(i);
                g.put(arr[i], l);
            }
        }

        // Hint 2: Start bfs from node 0 and keep distance, answer is the distance when you reach onode n-1.
        boolean[] visited = new boolean[n];
        int[] steps = new int[n];
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(0);
        steps[0] = 0;
        visited[0] = true;
        while (!q.isEmpty()) {
            int x = q.poll();
            if (x == n - 1) {
                break; // 剪枝1
            }
            int s = steps[x];
            if (x + 1 < n && !visited[x + 1]) {
                q.addLast(x + 1);
                steps[x + 1] = s + 1;
                visited[x + 1] = true;
            }
            if (x - 1 >= 0 && !visited[x - 1]) {
                q.addLast(x - 1);
                steps[x - 1] = s + 1;
                visited[x - 1] = true;
            }
            LinkedList<Integer> ii = g.get(arr[x]);
            for (int j : ii) {
                if (j != x && !visited[j]) {
                    q.addLast(j);
                    steps[j] = s + 1;
                    visited[j] = true;
                }
            }
            ii.clear(); // CRITICAL: 很重要的剪枝，因为这个路径不可能用两次
        }
        return steps[n - 1];
    }
}
