	import java.math.BigInteger;
	import java.util.Scanner;
	
	public class Main {
		
	
		public static void main(String[] args) {
			
			Scanner in = new Scanner(System.in);
			BigInteger n = in.nextBigInteger();
			BigInteger ans = BigInteger.ZERO;
			BigInteger pos = BigInteger.ONE;
			BigInteger TWO = new BigInteger("2");
//			while (pos.compareTo(n)<=0) {
//				ans = ans.add(n.divide(pos));
//				pos = pos.multiply(TWO);
//			}
			System.out.println(n.multiply(TWO).subtract(BigInteger.ONE));

		}
	}