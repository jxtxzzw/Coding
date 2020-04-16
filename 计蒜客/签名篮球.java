import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M, N;
        M = in.nextInt();
        N = in.nextInt();
        if (M % (N + 1) == 0) {
            System.out.println("none");
        } else {
            int i = M % (N + 1);
            System.out.print(i);
            for (i = M + 1; i <= N; i++) {
                System.out.print(" " + i);
            }
            System.out.println();
        }
    }
}
