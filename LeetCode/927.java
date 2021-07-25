class Solution {
    public int[] threeEqualParts(int[] arr) {
        // 不重不漏考虑全部情况：核心是 1 的个数要相等

        // 先统计 1 的个数，知道需要多少个 1 并剪枝不可能的情况
        int n = arr.length;

        int count = 0;
        for (int a : arr) {
            count += a;
        }

        if (count % 3 != 0) {
            return new int[] {-1, -1};
        }

        int target = count / 3;
        if (target == 0) {
            return new int[] {0, n - 1};
        }

        // 划分为三个部分
        int[] split = new int[6];
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                c++;
                for (int k = 0; k < 3; k++) {
                    if (c == k * target + 1) {
                        split[2 * k] = i;
                    }
                    // 这里不是 else 的关系
                    if (c == (k + 1) * target) {
                        split[2 * k + 1] = i;
                    }
                }
            }
        }
        // 1 的个数不等，一定不满足，但是 1 的个数相等也不一定满足，要检查
        // 由于我们跳过了前导 0，所以可以直接比较数组是不是一样
        int[][] parts = new int[3][];
        for (int k = 0; k < 3; k++) {
            parts[k] = Arrays.copyOfRange(arr, split[2 * k], split[2 * k + 1] + 1);
        }
        if (!Arrays.equals(parts[0], parts[1]) || !Arrays.equals(parts[1], parts[2])) {
            return new int[] {-1, -1};
        }

        // 最后一部分的 0 不可能作为前导零，所以中间 0 的个数必须大于这个数字，这样才能在每个部分尾部加上 0，多余的作为前导
        if (split[2] - split[1] < n - split[5] || split[4] - split[3] < n - split[5]) {
            return new int[] {-1, -1};
        }

        return new int[] {split[1] + n - split[5] - 1, split[3] + n - split[5]};
    }
}
