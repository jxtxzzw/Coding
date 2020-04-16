
//import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cnt = 0;
		while (in.hasNextInt()) {
			int m = in.nextInt();
			// StringBuffer sb = new StringBuffer();
			// sb.append('1');
			// for (int i=0;i<m;i++){
			// sb.append('0');
			// }
			// BigInteger bin = new BigInteger(sb.toString(),2);
			// System.out.println("Case #"+(++cnt)+":
			// "+(bin.toString().length()-1));
			System.out.println("Case #" + (++cnt) + ": " + (int) (m * Math.log10(2)));
		}

	}

}