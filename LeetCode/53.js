var maxSubArray = function(nums) {
    let pre = 0;
    return Math.max(...nums.map((x) => {pre = Math.max(pre + x, x); return pre;}));
};
