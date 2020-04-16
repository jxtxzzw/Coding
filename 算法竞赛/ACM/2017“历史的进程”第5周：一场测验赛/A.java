import java.util.Scanner;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int row = in.nextInt();
		int col = in.nextInt();
		int ans;
		if (row%2==0){
			ans = row/2*col; 
		} else {
			if (col%2==0){
				ans=col/2*row;
			} else {
				ans=col/2*row+(row/2)+1;
			}
		}
		System.out.println(ans);
}

}