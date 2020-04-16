import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numberOfDays = in.nextInt();
		int doubledDays = in.nextInt();
		int [] numberOfItems = new int[numberOfDays];
		int [] numberOfCustomers = new int[numberOfDays];
		int [] numberOfIncreasement = new int[numberOfDays];
		long totalAmount = 0;

		for (int i=0;i<numberOfDays;i++){
			numberOfItems[i] = in.nextInt();
			numberOfCustomers[i] = in.nextInt();
			numberOfIncreasement[i] = Math.min(numberOfItems[i]*2, numberOfCustomers[i])- Math.min(numberOfItems[i], numberOfCustomers[i]);
			totalAmount += Math.min(numberOfItems[i], numberOfCustomers[i]); 
		}
		
		Arrays.sort(numberOfIncreasement);
		
		for (int i=numberOfIncreasement.length-1;i>=numberOfIncreasement.length-doubledDays;i--){
			totalAmount += numberOfIncreasement[i];
		}
		
		System.out.println(totalAmount);

	}
}
