class Solution {
public:

    int n = 0;
    int C[500007] = {0}; // 树状数组
    int x[500007] = {0}; // 记录原始数据
    int dis[500007] = {0}; // 离散化数据

    int lowBit(int x) {
        return x & -x; // return x & (x ^ (x - 1))
    }

    int getSum(int x) {
        int res = 0;
        while (x != 0) {
            res += C[x];
            x -= lowBit(x);
        }
        return res;
    }

    void change(int x) {
        while (x <= n) {
            C[x]++;
            x += lowBit(x);
        }
    }

    vector<int> countSmaller(vector<int>& nums) {
        n = nums.size();
        
        vector<int> ans(n);
        if (n == 0) {
            return ans;
        }

        for (int i = 0; i < n; i++) {
            dis[i] = nums[i]; // 读入数据，复制一份以便离散化处理
        }
        sort(dis, dis + n); // 对数据排序
        int length = unique(dis, dis + n) - dis; // 利用 unique 去重，使大小与下标对应，并得到去重后的长度
        for (int i = 0; i < n; i++) {
            int index = lower_bound(dis, dis + length, nums[i]) - dis; // 在去重后的数组中，使用二分查找找到 x[i] 所在位置
            nums[i] = index + 1; // 用这个位置作为离散后的值，由于树状数组下标从 1 开始，因此加 1
        }
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = getSum(nums[i]);
            change(nums[i] + 1);
        }
        return ans;
    }
};