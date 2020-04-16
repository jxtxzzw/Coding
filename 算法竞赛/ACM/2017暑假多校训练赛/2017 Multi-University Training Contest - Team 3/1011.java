//by - zzw

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int days = in.nextInt();
        int counter = 0;
        for (int i=0;i<days;i++){
            if (in.nextInt()<=35) counter++;
        }
        System.out.println(counter);
                

    }

}