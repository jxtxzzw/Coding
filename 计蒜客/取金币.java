import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final double WYTHOFF = (1 + Math.sqrt(5)) / 2;
        int a, b;
        a = in.nextInt();
        b = in.nextInt();
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        int k = b - a;
        int ak = (int) (k * WYTHOFF);
        int bk = (int) (ak + k);
        System.out.println(a == ak && b == bk ? "0" : "1");
    }
}
