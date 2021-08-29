class Solution {
    public int minPatches(int[] nums, int n) {
        long canReach = 0;
        int missing = 0;
        for (int num : nums) {
            while (num > canReach + 1 && canReach < n) {
                missing++;
                canReach += canReach + 1;
            }
            canReach += num;
            if (canReach >= n) {
                break;
            }
        }
        while (canReach < n) {
            missing++;
            canReach += canReach + 1;
        }
        return missing;
    }
}
