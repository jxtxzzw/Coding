/**
 * 读入n个数
 * 求出这n个数的最大公约数
 * 逃不掉了
 * 所以这里没有办法省下来
 * 
 * 但是在知道了n个数的最大公约数以后
 * 不能从k-1一直试到0
 * 这是因为万一k很大的时候会TLE
 * 
 * 这时候可以考虑n个数的最大公约数分解质因数
 * gcd=a*a*b*c
 * 那么我要找的x与gcd的最大公约数大于1
 * 这就说明x是a的倍数
 * 或者是b的倍数
 * 或者是c的倍数
 * 所以我只要试一试a、2a、3a...b、2b...c、2c...
 * 又需要最大的，所以如果3a是小于k的那么就不用试a和2a了
 * 那么到底(?)*a是小于k的最大的呢
 * k整除a再乘上a
 * 例如k=17
 * 则17/2*2=16就是2、4、6、8...14、16最大的2的倍数
 * 
 * 又显然2*2*2*3*3*5这种情况
 * 2只要算一次就够了
 * 
 */
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int gcd = in.nextInt();
		n--;
		while (n-- != 0) {
			gcd = gcd(gcd, in.nextInt());
			if (gcd == 1)
				break;
		}
		// 至此，读入数据并完成n个数的最大公约数
		HashSet<Integer> fact = new HashSet<Integer>();
		factorize(gcd, fact);
		// 分解质因数
		Object[] a = fact.toArray();
		int ans = k / (Integer) a[0] * (Integer) a[0];
		for (int i = 1; i < a.length; i++)
			ans = Math.max(ans, k / (Integer) a[i] * (Integer) a[i]);
		// 找出可能的取值
		// 在里面找最大的
		System.out.println(ans);
	}

	/**
	 * @param a 数字1
	 * @param b 数字2
	 * @return
	 */
	private static int gcd(int a, int b) {
		return (b > 0) ? gcd(b, a % b) : a;
	}

	/**
	 * @param n 需要被分解的数
	 * @param fact 分解的结果放在哪里（本题采用了HashSet是因为可以不考虑重复）
	 */
	private static void factorize(int n, HashSet<Integer> fact) {
		int i = 2;
		while (i * i <= n) {
			if (n % i == 0) {
				n /= i;
				fact.add(i);
				i = 1;
			}
			i++;
		}
		fact.add(n);
	}

}