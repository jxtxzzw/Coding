class BIT {
    private int[] C;

    public BIT(int size) {
        C = new int[size + 1];
    }

    private int lowBit(int x) {
        return x & -x;
    }

    public int getSum(int i) {
        i += 1; // offset for BIT
        int sum = 0;
        while (i != 0) {
            sum += C[i];
            i -= lowBit(i);
        }
        return sum;
    }

    public int getSum(int l, int r) {
        return getSum(r) - getSum(l - 1);
    }

    public void change(int i, int diff) {
        i += 1; // offset for BIT
        while (i < C.length) {
            C[i] += diff;
            i = i + lowBit(i);
        }
    }
}


class NumArray {
    
    private BIT bit;
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
        bit = new BIT(nums.length);
        for (int i = 0; i < nums.length; i++) {
            bit.change(i, nums[i]);
        }
    }

    public void update(int index, int val) {
        int diff = val - nums[index];
        nums[index] = val;
        bit.change(index, diff);
    }

    public int sumRange(int left, int right) {
        return bit.getSum(left, right);
    }
    
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
