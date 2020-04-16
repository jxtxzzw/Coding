// By - Zhenwei Zhang
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int MAXN = 100000;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long[] a = new long[MAXN + 7];
        for (long i = 4; i <= MAXN; ++i) {
            if (i % 2 == 0) {
                if (i % 4 == 0) {
                    a[(int) i] = i * i / 8;
                } else {
                    a[(int) i] = 2 * (i / 4) * (i / 4 + 1);
                }
            } else {
                if ((i - 1) % 4 == 0) {
                    a[(int) i] = a[(int) (i - 1)] + i / 4 - 1;
                } else {
                    a[(int) i] = a[(int) (i - 1)] + i / 4;
                }
            }
        }
//        System.out.println(Arrays.toString(a));
        int caseNumber = in.nextInt();
        while (caseNumber-- != 0) {
            long number = in.nextLong();
            for (int i = 4; i <= MAXN; i++) {
                if (a[i - 1] < number && number <= a[i]) {
                    System.out.println(i);
                    break;
                }
            }
        }

    }
}