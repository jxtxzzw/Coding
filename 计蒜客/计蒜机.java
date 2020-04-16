import java.util.PriorityQueue;
import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s;
        PriorityQueue<Register> pq = new PriorityQueue<>();
        while (true) {
            s = in.nextLine();
            if (s.equals("#")) {
                break;
            }
            String[] ss = s.split(" ");
            int num = Integer.parseInt(ss[1]);
            int period = Integer.parseInt(ss[2]);
            pq.add(new Register(num, period, period));
        }
        int k = in.nextInt();
        for (int i = 0; i < k; i++) {
            Register r = pq.poll();
            assert r != null;
            System.out.println(r.getNum());
            pq.add(r.next());
        }
     }
}

class Register implements Comparable<Register> {
    private int num;
    private int period;
    private int time;

    public Register(int num, int period, int time) {
        this.num = num;
        this.period = period;
        this.time = time;
    }

    public Register next() {
        return new Register(num, period, time + period);
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Register r) {
        if (time != r.time) {
            return time - r.time;
        } else {
            return num - r.num;
        }
    }

    @Override
    public String toString() {
        return "Register{" +
                "num=" + num +
                ", period=" + period +
                ", time=" + time +
                '}';
    }
}
