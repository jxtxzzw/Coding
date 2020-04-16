import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] w = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        int[][] root = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = in.nextInt();
            dp[i][i] = w[i];
            root[i][i] = i;
        }
        for (int length = 2; length <= n; length++) {
            for (int i = 1; i <= n - length + 1; i++) {
                int j = i + length - 1;
                int s_left, s_right;
                for (int k = i; k <= j; k++) {
                    s_left = k == i ? 1 : dp[i][k - 1];
                    s_right = k == j ? 1 : dp[k + 1][j];
                    int s = s_left * s_right + w[k];
                    if (s > dp[i][j]) {
                        dp[i][j] = s;
                        root[i][j] = k;
                    }
                }
            }
        }
        System.out.println(dp[1][n]);
        printAnswer(root, 1, n);
    }

    private static void printAnswer(int[][] root, int left, int right) {
        if (left <= right) {
            System.out.print(root[left][right] + " ");
            printAnswer(root, left, root[left][right] - 1);
            printAnswer(root, root[left][right] + 1, right);
        }
    }
}
