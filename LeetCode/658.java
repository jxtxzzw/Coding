class Solution {
   public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 二分找到位置，然后取中间的 k 个数字
        // 更惊艳的做法是二分两个端点，如果左边远了，左端点右移，如果右边远了，右端点左移
        int left = 0;
        int right = arr.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
