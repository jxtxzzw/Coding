class Solution {
    // 基本算法是 BFS，但是要充斥着大量的优化
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // 特殊情况不需要计算
        if (source == target) {
            return 0;
        }

        // 排序之后再找，速度会更快，因为查找相同值只需要线性时间，而查找目标值可以二分
        for (int[] ints : routes) {
            Arrays.sort(ints);
        }

        // 先遍历一次，记录下哪些线路之间是可以到达的，之后不再需要多重循环去找是不是相交
        ArrayList<ArrayList<Integer>> canReach = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            canReach.add(new ArrayList<>());
        }
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes.length; j++) {
                if (intersect(routes[i], routes[j])) {
                    canReach.get(i).add(j);
                    canReach.get(j).add(i);
                }
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> count = new LinkedList<>();
        LinkedList<Integer> ans = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        // 起点可能有多个，所以不能简单地 q.add(source)，而要找到所有的点
        // 终点可能有多个，同理
        for (int i = 0; i < routes.length; i++) {
            // 排序后的数组查找 contains 直接二分
            if (Arrays.binarySearch(routes[i], source) >= 0) {
                queue.add(i);
                visited.add(i);
                count.add(1);
            }
            if (Arrays.binarySearch(routes[i], target) >= 0) {
                ans.add(i);
            }
        }

        // 至此，完成 BFS 的初始化，开始 BFS
        while (!queue.isEmpty()) {
            assert !count.isEmpty();

            int n = queue.poll();
            int c = count.poll();

            if (ans.contains(n)) {
                return c;
            }
            // 由于已经预处理了 n 所在的线路可以到达哪些线路，所以没必要重新遍历
            for (int x : canReach.get(n)) {
                if (!visited.contains(x)) {
                    visited.add(x);
                    queue.add(x);
                    count.add(c + 1);
                }
            }
        }
        return -1;
    }


    // 这里的关键是对排序后的数组查找相同值，只需要 O(n) 而不需要 O(n^2)
    private boolean intersect(int[] A, int[] B) {
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) {
                return true;
            }
            if (A[i] < B[j]) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }
}