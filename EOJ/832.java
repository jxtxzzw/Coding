
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(in.nextInt(), in.nextInt());
        }
        Arrays.sort(cows, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.getX() - o2.getX();
            }
        });
        HashSet<Cow> crowdedCowCandidate = new HashSet<>();
        PriorityQueue<Cow> q = new PriorityQueue<>();
        for (Cow c : cows) {
            while (!q.isEmpty() && q.peek().getX() < c.getX() - d) {
                q.poll();
            }
            if (!q.isEmpty() && q.peek().getH() >= c.getH() * 2) {
                crowdedCowCandidate.add(c);
            }
            q.add(c);
        }
        Arrays.sort(cows, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o2.getX() - o1.getX();
            }
        });
        q = new PriorityQueue<>();
        int ans = 0;
        for (Cow c : cows) {
            while (!q.isEmpty() && q.peek().getX() > c.getX() + d) {
                q.poll();
            }
            if (!q.isEmpty() && q.peek().getH() >= c.getH() * 2) {
                if (crowdedCowCandidate.contains(c)) {
                    ans++;
                }
            }
            q.add(c);
        }
        System.out.println(ans);
    }
}

class Cow implements Comparable<Cow> {

    private int x;
    private int h;

    Cow(int x, int h) {
        this.x = x;
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getH() {
        return h;
    }

    @Override
    public int compareTo(Cow o) {
        return o.h - h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cow cow = (Cow) o;
        return x == cow.x &&
                h == cow.h;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, h);
    }

    @Override
    public String toString() {
        return "Cow{" +
                "x=" + x +
                ", h=" + h +
                '}';
    }
}