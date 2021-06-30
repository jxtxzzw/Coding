import java.util.*;

class BIT {
    int[] C;

    public BIT(int size) {
        C = new int[size + 1];
    }

    public int lowBit(int x) {
        return x & -x;
    }

    public int sum(int x) {
        x++;
        int sum = 0;
        for (int i = x; i != 0; i -= lowBit(i)) {
            sum += C[i];
        }
        return sum;
    }

    public void update(int x, int diff) {
        x++;
        for (int i = x; i < C.length; i += lowBit(i)) {
            C[i] += diff;
        }
    }

    public int sum(int x, int y) {
        return sum(y) - sum(x - 1);
    }
}

public class LC {

    public List<Integer> countSmaller(int[] nums) {
        // 去重 + 排序
        TreeSet<Integer> ts = new TreeSet<>();
        for (int n: nums) {
            ts.add(n);
        }
        // 记录下每个数字是从小到大第几个，因为我们要做数据离散化，避免数据过大、数组太长
        int count = 1;
        HashMap<Integer, Integer> tm = new HashMap<>();
        for (int n : ts) {
            tm.put(n, count++);
        }
        LinkedList<Integer> ans = new LinkedList<>();
        // 用树状数组，转化为前缀和的问题，从而支持动态更新
        BIT bit = new BIT(ts.size() + 1);
        for (int i = nums.length - 1; i >= 0; i--) {
            // 数字 num 是第 rank 位的数字
            int num = nums[i];
            int rank = tm.get(num);
            // 树状数组该位置上的数字 + 1
            bit.update(rank, 1);
            // 现在问，比 rank 小的数字有多少个，那就是树状数组中 1~rank-1 的和
            ans.addFirst(bit.sum(1, rank - 1));
        }
        return ans;
    }

    public static void main(String[] args) {
        LC lc = new LC();
//        int[] nums = {5,2,6,1};
//        int[] nums = {5,2,6,1};
        int[] nums = {-1,-1};
        System.out.println(lc.countSmaller(nums));
    }
}



// class Solution {
// public:

//     int n = 0;
//     int C[500007] = {0}; // 树状数组
//     int x[500007] = {0}; // 记录原始数据
//     int dis[500007] = {0}; // 离散化数据

//     int lowBit(int x) {
//         return x & -x; // return x & (x ^ (x - 1))
//     }

//     int getSum(int x) {
//         int res = 0;
//         while (x != 0) {
//             res += C[x];
//             x -= lowBit(x);
//         }
//         return res;
//     }

//     void change(int x) {
//         while (x <= n) {
//             C[x]++;
//             x += lowBit(x);
//         }
//     }

//     vector<int> countSmaller(vector<int>& nums) {
//         n = nums.size();
        
//         vector<int> ans(n);
//         if (n == 0) {
//             return ans;
//         }

//         for (int i = 0; i < n; i++) {
//             dis[i] = nums[i]; // 读入数据，复制一份以便离散化处理
//         }
//         sort(dis, dis + n); // 对数据排序
//         int length = unique(dis, dis + n) - dis; // 利用 unique 去重，使大小与下标对应，并得到去重后的长度
//         for (int i = 0; i < n; i++) {
//             int index = lower_bound(dis, dis + length, nums[i]) - dis; // 在去重后的数组中，使用二分查找找到 x[i] 所在位置
//             nums[i] = index + 1; // 用这个位置作为离散后的值，由于树状数组下标从 1 开始，因此加 1
//         }
//         for (int i = n - 1; i >= 0; i--) {
//             ans[i] = getSum(nums[i]);
//             change(nums[i] + 1);
//         }
//         return ans;
//     }
// };


