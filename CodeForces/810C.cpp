/**
 * 以下代码在第8组测试数据超时
 * 
 * 一开始就是暴力枚举
 * 每个集合，只要知道MAX和MIN，所以中间的怎么取并不关心
 * 对于排序后的数组，a[j]-a[i]就是需要被加到SUM中的
 * 要被加几次，就是看中间有多少种情况
 * 在中间有j-i-1个数，即2^(j-i-1)次
 * 于是每次SUM就是加上(a[j]-a[i])*(Math.pow(2,j-i-1))
 * 
 */

/*
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	private final static BigInteger MOD = new BigInteger("1000000007");
	private final static BigInteger TWO = new BigInteger("2");

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int [] c = new int [n];
		for (int i=0;i<c.length;i++){
			c[i]=in.nextInt();
		}
		Arrays.sort(c);
		BigInteger ans = BigInteger.ZERO;
		for (int b = 0; b < c.length; b++){
			for (int e = b + 1; e < c.length; e++){
				Integer v = c[e] - c[b];
				Integer d = e - b;
				ans = ans.add(new BigInteger(v.toString()).multiply(TWO.pow(d-1)));
			}
		}
		System.out.println(ans.mod(MOD));
	}
}
 */


/**
 * 上述代码超时的原因是两重循环，时间复杂度是O(n^2)
 * 后来看了柳天奕的代码（柳天奕也是受金恺翔启发），降低成了O(n)的复杂度
 * 核心思想是把SUM拆开来
 * 加法归加法做，减法归减法做
 * 例如对于 1 2 3 4 ... i ... j ... 这组数
 * i被加的次数就是i作为MAX的次数
 * i被减的次数就是i作为MIN的次数
 * 如果令t[0]=1且t[i]=t[i-1]*2
 * 那么显然t就是记录着需要被运算的次数
 * 对于c[i]，被加的次数就是(t[i]-1)，于是SUM加上c[i]*(t[i]-1)
 * 被减的次数就是(t[(n-1)-i])，于是SUM再减去(c[i]*(t[(n-1)-i]-1)
 * 因为都是加减法，所以每次运算的时候取MOD就可以了
 * 需要注意的有两点，一个是long，一个是减法的取模，下详
 * 由此，将O(n^2)的复杂度降低成O(n)，就不会超时了
 * 
 */

/**
 * 以下代码在第69组测试数据超时
 */

/*
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private final static long MOD = 1000000007;
	// 首先注意数据范围，int是不够的，要开成long

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = in.nextInt();
		}
		Arrays.sort(c);
		// 对数组排序
		long[] t = new long[n];
		t[0] = 1;
		for (int i = 1; i < n; i++) {
			t[i] = t[i - 1] * 2 % MOD;
		}
		// 计算要加或者减的次数，也就是令t[0]=1且t[i]=t[i-1]*2
		// 计算的时候就可以取MOD
		long ans = 0;
		// 注意答案的范围是long
		for (int i = 0; i < n; i++) {
			// 遍历n次
			ans += (c[i] * (t[i] - 1) % MOD) % MOD;
			// 对于c[i]，要加上(t[i]-1)次，即ans加上 (c[i]*(t[i]-1))
			// 加完就可以取模了
			ans += MOD;
			// 这里特别注意，因为下面是减法，所以可能变成负的了
			// 比如3，减去10，就变成-7
			// 减法的取模，负号是保留的
			// 所以为了避免这种问题，先给ans加上MOD，然后再去减
			ans -= (c[i] * (t[(n - 1) - i] - 1) % MOD) % MOD;
			// 这里也是一样，c[i]被减去(t[(n-1)-i]-1)次
			// 因为减法的时候直接就取MOD了，所以减数一定小于MOD
			// 而刚才的ans加上了一个MOD，这时候减去一个小于MOD的数，不可能为负
			
			// 这里如果不注意ans+=MOD这句，答案将会是错误的
		}

		System.out.println(ans % MOD);
		// 输出ans%MOD
	}
}

*/

/**
 * 这段代码其实已经是正确的
 * 却在第69组数据超时
 * 同样的代码用C++写一遍交上去，AC
 * 
 * 以下为上述第2段代码的C++实现，Accept
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <algorithm>

using namespace std;

long long MOD = 1000000007;
int c[300007] = {0};
long long t[300007] = {0};

int main() {
	int n;
	scanf ( "%d", &n );

	for ( int i = 0; i < n; i++ ) {
		scanf ( "%d", &c[i] );
	}

	sort ( c, c + n );
	t[0] = 1;

	for ( int i = 1; i < n; i++ ) {
		t[i] = t[i - 1] * 2 % MOD;
	}

	long long ans = 0;

	for ( int i = 0; i < n; i++ ) {
		ans += ( c[i] * ( t[i] - 1 ) % MOD ) % MOD;
		ans += MOD;
		ans -= ( c[i] * ( t[ ( n - 1 ) - i] - 1 ) % MOD ) % MOD;
	}

	printf ( "%lld\n", ans % MOD );
}






