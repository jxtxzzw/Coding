/**
 * 这是一道“水题”
 * 
 * 将给定的n个IP地址写成二进制
 * 得到n个32位的字符串
 * 
 * 从左往右检索
 * 相同的部分取出
 * 后面不同的部分全部是0
 * 这个新的32位的二进制串就是IP地址
 * 重新变成十进制的形式就是网络地址
 * 
 * 从左往右检索
 * 相同的部分取出并且全部变成1
 * 后面不同的部分全是0
 * 这个新的二进制串重新写成十进制就是子网掩码
 * 
 * 然而
 * Java竟然不支持二进制的格式化输入
 * 而且字符串不足位数左起补0也不是很方便
 * 所以下面很多都动用了StringBuffer来做
 * 其实
 * 这道题还不如直接用C语言来取二进制
 * 直接取模然后看余数
 * 这样反倒爽快
 * 
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			int n = in.nextInt();
			in.nextLine();
			String[][] ss = new String[n][4];
			
			for (int i = 0; i < n; i++) {
				ss[i] = in.nextLine().split("\\.");
				// 字符串分隔，变成4个int（其实还是String，可看成int）
				// 注意"."是逃逸字符，要用"\"表示，而"\"本身也是逃逸字符，要用"\\"表示，故"\\."
				// "\\."等价于"[.]"
			}
				
			StringBuffer[] sbs = new StringBuffer[n];
			for (int i = 0; i < n; i++) {
				sbs[i] = new StringBuffer();
				// 将n个IP地址变成二进制的形式
				// 不足的要补0
				for (int j = 0; j < 4; j++) {
					String s = Integer.toBinaryString(Integer.parseInt(ss[i][j]));
					for (int k = s.length(); k < 8; k++)
						sbs[i].append('0');
					sbs[i].append(s);
				}
			}
			
			// 找到相同部分的长度
			int len = 0;
			LOOP: for (int i = 0; i < 32; i++) {
				char t = sbs[0].charAt(i);
				for (int j = 1; j < n; j++) {
					// 逐个比较第i位
					if (sbs[j].charAt(i) != t)
						break LOOP;
					// 只要不一样了，就可以跳出整个大循环
				}
				len++;
			}
			
			// 得到IP地址
			StringBuffer ipAddr = new StringBuffer(sbs[0].substring(0, len));
			for (int i = ipAddr.length(); i < 32; i++) {
				ipAddr.append('0');
			}
			
			// 得到子网掩码
			StringBuffer ipMask = new StringBuffer();
			for (int i = 0; i < len; i++) {
				ipMask.append('1');
			}
			for (int i = ipMask.length(); i < 32; i++) {
				ipMask.append('0');
			}
			
			// 将IP地址和子网掩码变成十进制输出
			for (int i = 0; i < 4; i++) {
				System.out.print(Integer.parseInt(ipAddr.substring(8 * i, 8 * (i + 1)), 2));
				System.out.printf("%c", i == 3 ? '\n' : '.');
			}
			for (int i = 0; i < 4; i++) {
				System.out.print(Integer.parseInt(ipMask.substring(8 * i, 8 * (i + 1)), 2));
				System.out.printf("%c", i == 3 ? '\n' : '.');
			}
		}
	}
}
