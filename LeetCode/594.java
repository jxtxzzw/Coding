class Solution {
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int k : map.keySet()) {
            if (map.containsKey(k + 1)) {
                ans = Math.max(ans, map.get(k) + map.get(k + 1));
            }
        }
        return ans;
    }
}