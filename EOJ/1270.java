import java.util.HashMap;
import java.util.Scanner;

/*
 * 这道题的最短路径是要用乘法的
 * 不妨做如下解释
 * 如果A能够换2B、B能够换3C
 * 那么A能够换1*2*3=6C
 * 所以只要把Floyd的[i][k]+[k][j]换成[i][k]*[k][j]即可
 * 另外，因为算的是获利，所以应该取两者较大值
 * 下面还要考虑初始化的问题
 * 因为不是取Min，所以一开始肯定不是INFINITY
 * 显然，由生活实际知，自己兑换自己，一定是汇率为1（不考虑中间获利）
 * 所以row==col的时候就是1
 * 而货币的兑换是单向的
 * A能换B不意味着B能等价换A
 * 可能B不能换A，或者B换A的汇率不一样
 * 所以row!=col就全部是0
 * 判断是不是能获利
 * 就是看是不是有一个货币，自己换自己，能大于1
 * 
 */

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numberOfCurrencies;
		int caseNumber = 0;
		// 多组输入，直到文件结束
		while ((numberOfCurrencies = in.nextInt()) != 0) {
			caseNumber += 1;
			// 汇率数组
			double[][] exchangeRates = new double[numberOfCurrencies][numberOfCurrencies];
			for (int row = 0; row < exchangeRates.length; ++row) {
				for (int col = 0; col < exchangeRates[row].length; ++col) {
					if (row == col)
						// 自己兑换自己汇率是1，其他全是0
						exchangeRates[row][col] = 1;
					else
						exchangeRates[row][col] = 0;
				}
			}
			// 用Map完成映射
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (int index = 0; index < numberOfCurrencies; ++index) {
				String currencyName = in.next();
				map.put(currencyName, index);
			}
			int lengthOfTheTable = in.nextInt();
			while (lengthOfTheTable-- != 0) {
				// 读入汇率表
				String sourceCurrencyName = in.next();
				double exchangeRate = in.nextDouble();
				String destinationCurrencyName = in.next();
				// 转化为int
				int sourceCurrencyIndex;
				int destinationCurrencyIndex;
				sourceCurrencyIndex = map.get(sourceCurrencyName);
				destinationCurrencyIndex = map.get(destinationCurrencyName);
				// 汇率是单向的
				exchangeRates[sourceCurrencyIndex][destinationCurrencyIndex] = exchangeRate;
			}
			// Floyd改成乘法，并取最大值
			for (int k = 0; k < numberOfCurrencies; ++k) {
				for (int i = 0; i < numberOfCurrencies; ++i) {
					for (int j = 0; j < numberOfCurrencies; ++j) {
						exchangeRates[i][j] = Math.max(exchangeRates[i][j], exchangeRates[i][k] * exchangeRates[k][j]);
					}
				}
			}
			// 判断是不是能获利
			// 就是看是不是有一个货币，自己换自己，能大于1
			boolean isArbitragePossible = false;
			for (int currencyIndex = 0; currencyIndex < numberOfCurrencies; ++currencyIndex) {
				isArbitragePossible = exchangeRates[currencyIndex][currencyIndex] > 1;
				if (isArbitragePossible)
					break;
			}
			System.out.println("Case " + caseNumber + ": " + (isArbitragePossible ? "Yes" : "No"));

		}

	}
}
