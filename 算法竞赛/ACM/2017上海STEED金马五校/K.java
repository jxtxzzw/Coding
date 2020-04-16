import java.util.Scanner;
 
public class Main {
 
    private static int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
 
    private static boolean judge(Integer[][] s, int row, int col, int direction, int n, int m) {
        if (row + directions[direction][0] >= n || col + directions[direction][1] >= m
                || row + directions[direction][0] < 0 || col + directions[direction][1] < 0
                || s[row + directions[direction][0]][col + directions[direction][1]] != null) {
            return false;
        } else {
            return true;
        }
    }
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            Integer num[] = new Integer[n * m];
            for (int i = 0; i < n * m; i++)
                num[i] = in.nextInt();
            Integer[][] s = new Integer[n][m];
            
//            System.out.println(s[1][1]);
            int pos = 0;
            int row = 0;
            int col = 0;
            int direction = 0;
            while (pos < n * m) {
                s[row][col] = num[pos++];
                if (judge(s, row, col, direction, n, m) == true) {
                    row += directions[direction][0];
                    col += directions[direction][1];
                } else {
                    direction = (direction + 1) % 4;
                    row += directions[direction][0];
                    col += directions[direction][1];
                }
            }
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s[i].length; j++) {
                    System.out.printf("%d%c", s[i][j], j < s[i].length - 1 ? ' ' : '\n');
                }
            }
 
        }
    }
}