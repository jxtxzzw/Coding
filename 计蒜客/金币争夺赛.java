import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int nim = in.nextInt();
        for (int i = 1; i < n; i++) {
            nim ^= in.nextInt();
        }
        System.out.println(nim != 0 ? "Yes" : "No");
    }
}
