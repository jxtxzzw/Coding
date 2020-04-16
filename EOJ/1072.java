import java.util.Scanner;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.nextLine();
		TreeSet<String> set = new TreeSet<String>();
		while (in.hasNextLine()){
			set.add(in.nextLine());
		}
		for (String s : set){
			System.out.println(s);
		}
	}

}
