class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int s: stones) {
            pq.add(s);
        }
        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();
            if (x != y) {
                pq.add(x - y);
            }
        }
        if (pq.size() == 0) {
            return 0;
        }
        return pq.peek();
    }
}