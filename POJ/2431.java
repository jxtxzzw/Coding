import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 有N个加油站，每个加油站距离终点的距离是D，可以加F升油，车现在距离终点L，还有P升油，问最少需要加几次油能到终点
 * 优先级队列，在油量不足的时候选最多的那个加油站加油 具体操作：在经过加油站时，把加油站加入优先级队列，当燃料为空，取出队列中最大元素加油，队列为空则不可行
 * 注意终点也要处理成加油站，注意排序
 *
 */

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		FuelStop[] fuelStops = new FuelStop[n + 1];

		for (int i = 0; i < n; ++i) {
			int distanceFromTown = in.nextInt();
			int fuelAvaliable = in.nextInt();
			fuelStops[i] = new FuelStop(distanceFromTown, fuelAvaliable);
		}

		// 为了便于计算，把终点也看成加油站，这个加油站距离终点为0，不能加油
		fuelStops[n] = new FuelStop(0, 0);

		Arrays.sort(fuelStops, FuelStop.distanceComparator());

		int l = in.nextInt();
		int p = in.nextInt();

		in.close();

		PriorityQueue<FuelStop> pq = new PriorityQueue<FuelStop>();
		int cnt = 0;

		for (int i = 0; i <= n; ++i) {
			int d = l - fuelStops[i].getDistanceFromTown();
			
			// 经过的加油站都是备选可以加的，具体选哪个，要看什么时候油量不足
			while (p < d && !pq.isEmpty()) {
				p += pq.poll().getFuelAvaliable(); // 油量不够，取最大的加油
				++cnt;
			}

			if (p < d) {
				cnt = -1; // 无法到达
				break;
			} else {
				// 到达下一个加油站，把加油站加入优先级队列
				p -= d;
				l -= d;
				pq.add(fuelStops[i]);
			}
		}

		System.out.println(cnt);

	}

}

class FuelStop implements Comparable<FuelStop> {
	private int distanceFromTown;
	private int fuelAvaliable;

	public FuelStop(int distanceFromTown, int fuelAvaliable) {
		this.distanceFromTown = distanceFromTown;
		this.fuelAvaliable = fuelAvaliable;
	}

	public int getDistanceFromTown() {
		return distanceFromTown;
	}

	@Override
	public String toString() {
		return "FuelStop [distanceFromTown=" + distanceFromTown + ", fuelAvaliable=" + fuelAvaliable + "]";
	}

	public int getFuelAvaliable() {
		return fuelAvaliable;
	}

	@Override
	public int compareTo(FuelStop that) {
		return that.fuelAvaliable - this.fuelAvaliable;
	}

	public static Comparator<FuelStop> distanceComparator() {
		return new Comparator<FuelStop>() {
			@Override
			public int compare(FuelStop fs1, FuelStop fs2) {
				return fs2.distanceFromTown - fs1.distanceFromTown;
			}

		};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + distanceFromTown;
		result = prime * result + fuelAvaliable;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuelStop other = (FuelStop) obj;
		if (distanceFromTown != other.distanceFromTown)
			return false;
		if (fuelAvaliable != other.fuelAvaliable)
			return false;
		return true;
	}

}
