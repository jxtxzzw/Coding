//by - zzw
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	// The answer is (( n ** k ) % MOD )
	// Don't know why
    
    public static void main(String[] args) {
        final BigInteger MOD = new BigInteger("1000000007");
        Scanner in = new Scanner(System.in);
        int cnt = 0;
        while (in.hasNext()){
            Long n = in.nextLong();
            Long k = in.nextLong();
            BigInteger bn = new BigInteger(n.toString());
            bn = bn.modPow(new BigInteger(k.toString()), MOD);
            // Here is a call of modPow(BigInteger, BigInteger)
            System.out.println("Case #"+(++cnt)+": "+bn);
            
        }
            
        }
    }

    // Note that the following codes will cause TLE
	    //            BigInteger bi = new BigInteger("1");
		//            for (long i = 0;i<k;i++){
		//                bi = bi.multiply(bn).mod(MOD);
		//            }
	// Another possible way is QuickPower, see what is provided by LTY            