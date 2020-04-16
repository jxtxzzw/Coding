import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] num = new int[n];
			for (int i = 0; i < n; i++) {
				num[i] = in.nextInt();
			}
			HashSet<Integer> set = new HashSet<Integer>();
			for (int t=0;t<Math.pow(2,n);t++){
				int[] c = new int[n];
				int tmp = t;
				for (int i=0;i<n;i++) {
					c[i]=tmp%2;
					tmp/=2;
				}
			
				int ans = 0;
				for (int i=0;i<n;i++){
					ans+=(c[i]==1?1:-1)*num[i];
				}
				set.add(ans);
			}
			System.out.println(set.size());
		}
	}
}