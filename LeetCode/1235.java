
/**
能看出 DP，能想出 DP
但是不能遍历找 previousJob，需要二分
这里的二分很容易写错
**/


class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;

        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {

            int notDoThis = i == 0 ? 0 : dp[i - 1];

            int previousJob = bs(jobs, i);
//            int previousJob = silly(jobs, i); /* 27 / 27 test cases passed, but took too long. */
            int doThis = (previousJob >= 0 ? dp[previousJob] : 0) + jobs[i][2];

            dp[i] = Math.max(notDoThis, doThis);
        }

        return dp[n - 1];

    }

    private int silly(int[][] jobs, int i) {
        int p = -1;
        for (int j = 0; j < i; j++) {
            if (jobs[j][1] <= jobs[i][0]) {
                p = j;
            }
        }
        return p;
    }

    private int bs(int[][] jobs, int i) {
        int l = 0;
        int r = i - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (jobs[mid][1] <= jobs[i][0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return (l < i && jobs[l][1] <= jobs[i][0]) ? l : -1;
    }
}
