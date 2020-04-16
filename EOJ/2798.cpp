#include <iostream>
#include <stdio.h>
#define MAX_FIBONACCI 92 // 经过尝试，最大不超过long long的Fibonacci是第91个
using namespace std;

long long fibonacci[MAX_FIBONACCI]; // 存放斐波那契数列
int isUsed[MAX_FIBONACCI]; // 存放数字拆解成不同的斐波那契数列的数的和

void generateFibonacci();

int main() {
	generateFibonacci();
	int caseNumber;
	scanf("%d",&caseNumber);
	for (int caseIndex = 0; caseIndex < caseNumber; ++caseIndex) {
		long long number;
		scanf("%lld",&number);
		for (int i=0; i<MAX_FIBONACCI; ++i) {
			isUsed[i]=0;
		}
		int index = MAX_FIBONACCI-1;
		/**
		 * 将number拆解成不同的fibonacci之和
		 * 具体做法是
		 * 从fibonacci的最大的一个开始向前遍历
		 * 如果某一个fibonacci数比number小
		 * 说明number可以写成fibonacci+restPart
		 * 其中restPart=number-fibonacci
		 * 于是操作就变成将restPart拆解成不同的fibonacci之和
		 * 改过程循环直到number被拆完
		 * 即number被减完
		 */
		while (number > 0) {
			while (fibonacci[index] > number) index--;
			number = number - fibonacci[index];
			isUsed[index] = 1;
		}
		int decompositionSchemes, schemes;
		int lastIndex = -1;
		for (int currentIndex = 1; currentIndex < MAX_FIBONACCI; ++currentIndex) {
			// 遍历，如果isUsed说明第currentIndex个fibonacci是刚才被拆出来的数
			if (isUsed[currentIndex] == 1) {
				// 如果这是第一次遇到
				if (lastIndex == -1) {
					// 计算这个数字有多少拆解成fibonacci的可能，显然为(currentIndex-1)除以2向下取整
					decompositionSchemes = (currentIndex - 1) / 2;
					// 这个数也是fibonacci，所以由他本身也是一种方案
					schemes = 1;
					// 更新lastIndex
					lastIndex = currentIndex;
					continue;
				}
				// 如果不是第一次遇到，就先把之前得到的方案数保存一下
				int tmp = decompositionSchemes + schemes;
				// 计算新的拆解数
				/**
				 * 注意到新的拆解方案数不能是(currentIndex-1)/2
				 * 因为小的数字被拆开了
				 * 所以大的数字最多只能拆到和小的数字一样小
				 * 不能继续拆下去
				 * 比如5已经被拆成2和3
				 * 则13最多拆到5和8就要停了
				 * 如果继续拆下去就有重复数字
				 * 所以要求出新增的拆解方案数
				 * 于是应该((currentIndex-1)/2)-((lastIndex-1)/2)
				 * 化简、合并同类项
				 * 得(currentIndex-lastIndex)/2
				 * 这是多出来的部分的拆解方案数
				 * 多出来这部分的每种方案都可以和之前的拆解方案数组合
				 * 运用乘法原理
				 * 得到新的拆解方案数是decompositionSchemes*((currentIndex-lastIndex)/2)
				 * 同理
				 * 由于这个数字本身也是一个fibonacci
				 * 所以由他本身组成的也是一种方案
				 * 而这种方案可以和之前的任何一种方案组合
				 * 这是因为这个fibonacci是到目前最大的
				 * 之前不可能把它拆解过
				 * 于是可以任意和任意一种方案组合
				 * 乘法原理
				 * 而schemes和decompositionSchemes是并列的，用加法原理
				 * 由此得到的是到目前为止所有的拆解方案数
				 * 这时候把之前算出的tmp认为是一个整体，而现在算出来的是拆解方案
				 */
				decompositionSchemes = schemes * ((currentIndex - lastIndex - 1) / 2) + decompositionSchemes * ((currentIndex - lastIndex) / 2);
				schemes = tmp;
				lastIndex = currentIndex;
			}
		}
		// 最后的答案就是schemes和decompositionSchemes的和
		printf("case #%d:\n%d\n", caseIndex, decompositionSchemes + schemes);
	}
	return 0;
}

/**
 * [generateFibonacci description] 生成斐波那契数列
 */
void generateFibonacci() {
	fibonacci[1] = 1;
	fibonacci[2] = 2;
	for (int i = 3; i < MAX_FIBONACCI; ++i)
		fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
}
