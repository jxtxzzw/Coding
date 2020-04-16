import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int sizeA = in.nextInt();
		int sizeB = in.nextInt();
		TreeSet<Integer> A = new TreeSet<Integer>();
		TreeSet<Integer> B = new TreeSet<Integer>();
		TreeSet<Integer> result = new TreeSet<Integer>();
		// 因为升序，所以TreeSet而不是HashSet
		while (sizeA-- != 0)
			A.add(in.nextInt());
		while (sizeB-- != 0)
			B.add(in.nextInt());

		// 交集
		result.clear();
		result.addAll(A);
		result.retainAll(B);
		print(result);

		// 并集
		result.clear();
		result.addAll(A);
		result.addAll(B);
		print(result);

		// 差集
		result.clear();
		result.addAll(A);
		result.removeAll(B);
		print(result);

	}

	private static void print(TreeSet<Integer> result) {
		String s = result.toString().replaceAll(" ", "").replaceAll("\\[", "{").replaceAll("\\]", "}");
		System.out.println(s);
	}

}
