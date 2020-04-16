import java.util.Scanner;

public class Main {

    private static final int INFINITY = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                map[i][j] = INFINITY;
                if (i == j) {
                    // 自己和自己是直接认识的，0 度好友
                    map[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            // 无向图
            map[a][b] = 1;
            map[b][a] = 1;
        }
        // 求一次最短路
        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    map[i][j] = Math.min(map[i][j], Math.max(map[i][k], map[k][j]));
                }
            }

        }
        boolean satisfied = true;
        for (int i = 1; i <= n; ++i) {
            // 找到第 i 个人认识其他人最多要通过几层关系
            int max = map[i][0];
            for (int j = 1; j <= n; ++j) {
                max = Math.max(max, map[i][j]);
            }
            // 经过六个人认识，就是最短路径为 7，所有大于 7 的最短路径都是失败的
            if (max > 7) {
                satisfied = false;
            }
        }
        System.out.println(satisfied ? "Yes" : "No");

    }

}
