import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            long dp[] = new long[n + 1];
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i <= n; ++i) {
                if (i < m) {
                    dp[i] = dp[i - 1] * 2;
                } else if (i == m) {
                    dp[i] = dp[i - 1] * 2 - 1;
                } else {
                    dp[i] = dp[i - 1] * 2 - dp[i - m - 1];
                }
            }
            System.out.println(dp[n]);
        }
    }
}

/*
 * 设dp[i]表示N个坑连续放M个会爆炸，现在放了前i个没有爆炸的方案数量
 * 对于dp[i-1]没有爆炸，[i]可以放也可以不放
 *
 * 首先考虑i<m的时候，这时候显然无论放还是不放都不会引起爆炸
 * 于是dp[i]=dp[i-1]*2
 *
 * 当i=m的时候，[i]可以放也可以不放
 * 如果不放的话，有dp[i-1]种方案
 * 如果放的话，先放上，然后排除会爆炸的方案
 * 只有1种情况需要排除，就是全放
 * 所以放的话有dp[i-1]-1种方案
 * 于是dp[i]=dp[i-1]*2-1
 *
 * 如果i>m，[i]可以放也可以不放
 * 如果不放的话，有dp[i-1]种方案
 * 如果放的话，先放上，然后排除放上会爆炸的方案
 * 放上会爆炸的可能就是[i-m+1]、[i-m+2]……[i-2]、[i-1]放了m-1个
 * 这时候[i]放上去，[i-m+1]~[i]，就连成了m个，爆炸
 * 而既然[i-m+1]、[i-m+2]……[i-2]、[i-1]是放了东西的
 * 那么[i-m]就一定没有放东西
 * 否则[i-m]~[i-1]就连成了m个了
 * 所以放上会爆炸的方案数量就是[i-m]没有放东西的方案数量
 * [i-m]没有放东西的数量，由上面的分析，就是dp[i-m-1]没有爆炸的时候选择[i-m]不放，即dp[i-m-1]种
 * 于是dp[i]=dp[i-1]*2-dp[i-m-1]
 *
 * 综上
 * dp[i]=dp[i-1]*2,i<m
 * dp[i]=dp[i-1]*2-1,i=m
 * dp[i]=dp[i-1]*2-dp[i-m-1],i>m
 *
 * 考虑边界值
 * 显然dp[1]=2
 * 为了配合程序正常运行，取dp[0]=1
 *
 */
