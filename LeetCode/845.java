
class Solution {
    public int longestMountain(int[] A) {
        int left = 0;
        int right = 1;
        int ans = 0;
        while (right < A.length) {
            while (right < A.length && A[right] <= A[right - 1]) {
                right++;
            }
            left = right - 1;
            while (right < A.length && A[right] > A[right - 1]){
                right++;
            }
            while (right < A.length && A[right] < A[right - 1]) {
                right++;
                ans = Math.max(ans, right - left);
            }
        }
        return ans;
    }
}
