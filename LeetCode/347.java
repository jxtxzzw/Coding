class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
         for (int num : nums ) {
             map.put(num, map.getOrDefault(num, 0) + 1);
         }

         PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
             @Override
             public int compare(Integer o1, Integer o2) {
                 return map.getOrDefault(o1, 0) - map.getOrDefault(o2, 0);
             }
         });

         for (int num: map.keySet()) {
             pq.add(num);
             if (pq.size() > k) {
                 pq.poll();
             }
         }


         List<Integer> list = new ArrayList<>(pq);
         Collections.reverse(list);
         return list;
    }
}
