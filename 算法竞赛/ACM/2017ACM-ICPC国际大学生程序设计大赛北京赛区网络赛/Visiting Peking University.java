// by - ZhangZhenwei
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			int m = in.nextInt();
			Integer [] a = new Integer[n];
			for (int i=0;i<a.length;i++) {
				a[i] = in.nextInt();
			}
			int q = in.nextInt();
			for (int i=0;i<q;i++) {
				a[in.nextInt()] = null;
			}
			//System.out.println(Arrays.toString(a));
			int min = -1;
			int mina = -1;
			int minb = -1;
			for (int i=0;i<a.length;i++) {
				int cnt = 0;
				for (int k=i+1;k<a.length;k++) {
					if (a[k]!=null) cnt++;
				}
				if (cnt+1<m) break;
				if (a[i]==null) continue;
				int tmp = -1;
				int tmpb = -1;
				for (int j=i+1,counter=1;counter < m;j++ ) {
					if (a[j]==null) continue;
					counter ++;
					if (tmp==-1) {
						tmp = a[j];
						tmpb = j;
					} else {
						if (tmp>a[j]) {
							tmp = a[j];
							tmpb = j;
						}
					}
				}
				tmp = tmp + a[i];
				if (min==-1 || min>tmp) {
					min = tmp;
					mina = i;
					minb = tmpb;
				}
				
			}
			
			System.out.println(mina+" "+minb);
			
			
		}

	}

}
