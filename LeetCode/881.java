class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        int l = 0;
        int r = people.length - 1;
        while (r >= 0 && people[r] > limit) {
            ans++;
            r--;
        }
        while (r >= l) {
            if (r != l && people[r] + people[l] <= limit) {
                l++;
            }
            ans++;
            r--;
        }
        return ans;
    }
}
