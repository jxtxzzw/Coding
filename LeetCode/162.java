// 看到复杂度想到二分，然后就不知道怎么做了……
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid + 1 == nums.length || nums[mid] > nums[mid + 1]) {
                // 说明峰值在左侧
                // 注意 nums[nums.length] 是负无穷
                right = mid;
            } else {
                // 说明峰值在右侧
                left = mid + 1;
            }
        }
        return left;
    }