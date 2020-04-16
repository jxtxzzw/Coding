import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		while (n-- != 0)
			pq.add(in.nextLong());
		long ans = 0; // 要开long
		while (pq.size() > 1) {
			long x = pq.poll();
			long y = pq.poll();
			ans += x + y;
			pq.add(x + y);
		}
		System.out.println(ans);
	}
}