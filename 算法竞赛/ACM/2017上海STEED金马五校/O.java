import java.util.Arrays;
import java.util.Scanner;
  
public class Main {
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            double[] num = new double[2 * n];
            for (int i = 0; i < 2 * n; i++) {
                num[i] = in.nextDouble();
            }
            Arrays.sort(num);
            double ans = 0;
            for (int i = 0; i < 2 * n - 1; i += 2) {
                ans += num[i + 1] - num[i];
            }
            System.out.printf("%.0f\n",ans);
        }
    }
}