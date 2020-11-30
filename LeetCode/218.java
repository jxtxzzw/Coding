class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> answer = new ArrayList<>();
        // 按坐标从左到右排序
        ArrayList<List<Integer>> list = new ArrayList<>();
        for (int[] building: buildings) {
            List<Integer> left = new ArrayList<>();
            left.add(building[0]);
            // 左端点取负数，确保左端点在右端点前面
            left.add(-building[2]);
            list.add(left);
            List<Integer> right = new ArrayList<>();
            right.add(building[1]);
            right.add(building[2]);
            list.add(right);
        }
        list.sort((o1, o2) -> o1.get(0).equals(o2.get(0)) ? Integer.compare(o1.get(1), o2.get(1)) : Integer.compare(o1.get(0), o2.get(0)));

        System.out.println(list);

        // 扫描线算法
        int last = -1;
        // 不能是 Set，要允许重复，而且要一个有序的东西，还要能快速取出最后一个元素的
        // C++ multiset 很巧妙，Java 用 TreeMap 的计数来实现重复
        TreeMap<Integer, Integer> heights = new TreeMap<>();
        for (List<Integer> l : list) {
            // 遍历，左端点入，右端点出
            if (l.get(1) < 0) {
                heights.put(-l.get(1), heights.getOrDefault(-l.get(1), 0) + 1);
            } else {
                heights.put(l.get(1), heights.getOrDefault(l.get(1), 0) - 1);
                if (heights.getOrDefault(l.get(1), 0) == 0) {
                    heights.remove(l.get(1));
                }
            }
            // 当前点的最高处
            int maxHeight;
            if (heights.isEmpty()) {
                // 0 的转折点也需要的
                maxHeight = 0;
            } else {
                maxHeight = heights.lastKey();
            }

            // 决定是否需要加入 answer，就看是不是发生了变化
            if (last != maxHeight) {
                ArrayList<Integer> ans = new ArrayList<>();
                ans.add(l.get(0));
                ans.add(maxHeight);
                answer.add(ans);
            }
            last = maxHeight;
        }
        return answer;
    }
}
