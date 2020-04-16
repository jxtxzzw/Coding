import java.util.Scanner;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int x = in.nextInt();
			int ans = 0;
			while (x>0){
			if (x%2==1){
				ans++;
				x--;
			}
			x/=2;
			}
			System.out.println(ans);
		}
	}

}
