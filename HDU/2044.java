/**
 * 显然不论如何编号
 * 蜜蜂走过的路都是一样的
 * 所以答案与起点终点无关
 * 只与走过的长度有关
 * 走过1个长度只有1个走法
 * 那就是走过去
 * 走过2个长度可以是直接走过去
 * 也就是直接向右移动
 * 也可以先走到右下再右上（或者右上再右下）
 * 一共是2种
 * 后面就像是走楼梯一样
 * 走过3个长度
 * 是走过1个长度再走过2个长度
 * 走过4个长度
 * 可以是走过3个长度再走过1个长度
 * 或者走过2个长度再走过2个长度
 * F(N)=F(N-1)+F(N-2)
 * 就是一个递推数列
 * 
 * 注意答案需要是long
 * 开int会Wrong Answer
 * 
 */

import java.util.Scanner;

public class Main {

	static int[] numberOfRoutes = new int[50];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		numberOfRoutes[1] = 1;
		numberOfRoutes[2] = 2;
		for (int i = 3; i < 50; i++) {
			numberOfRoutes[i] = numberOfRoutes[i - 1] + numberOfRoutes[i - 2];
		}
		while (caseNumber-- != 0) {
			System.out.println(numberOfRoutes[-(in.nextInt() - in.nextInt())]);
		}
	}
}
