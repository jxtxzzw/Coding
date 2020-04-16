/**
 * 因为1000是8的倍数
 * 所以只要看最后3位是不是能够被8整除
 * 由题意知这是Special Judge
 * 因此可以直接取出某3位
 * 看这个三位数是不是能够被8整除
 * 如果可以
 * 那么就把其他的都删掉
 * 最后的答案就是这个三位数
 * 除了三位数还应该注意判断两位数和一位数
 * 其中判断一位数的时候
 * 下面采用的方法与判断两位数和三位数一致
 * 其实可以直接判断String s是不是包含8或者0
 * 只要有8或者0就可以直接输出答案8或者0
 * 
 */
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int num = 0;
		boolean found = false;
		/*
		 * 先看一位数
		 * 取出一位数
		 * 变成int
		 * 看是不是能够被8整除
		 * 如果可以就输出
		 * 
		 * 这里还可以直接判断s是不是contain了0或者8
		 * 
		 */
		if (!found)
			LOOP: for (int i = 0; i < s.length(); i++) {
				num = Integer.parseInt("" + s.charAt(i));
				if (num % 8 == 0) {
					System.out.println("YES");
					System.out.println(num);
					found = true;
					break LOOP;
				}
			}
		/*
		 * 再看两位数位数
		 * 二重循环取出2个数
		 * 然后组合并且变成int
		 * 看是不是能够被8整除
		 * 如果可以就输出
		 * 
		 */
		if (!found)
			LOOP: for (int i = 0; i < s.length(); i++)
				for (int j = i + 1; j < s.length(); j++) {
					num = Integer.parseInt("" + s.charAt(i) + s.charAt(j));
					if (num % 8 == 0) {
						System.out.println("YES");
						System.out.println(num);
						found = true;
						break LOOP;
					}
				}
		/*
		 * 三位数同理
		 * 
		 */
		if (!found)
			LOOP: for (int i = 0; i < s.length(); i++)
				for (int j = i + 1; j < s.length(); j++)
					for (int k = j + 1; k < s.length(); k++) {
						num = Integer.parseInt("" + s.charAt(i) + s.charAt(j) + s.charAt(k));
						if (num % 8 == 0) {
							System.out.println("YES");
							System.out.println(num);
							found = true;
							break LOOP;
						}
					}
		if (!found) {
			System.out.println("NO");
		}
	}
}
