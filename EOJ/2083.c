#include <stdio.h>
#include <stdlib.h>

#define MAXN 57

/**
 * 这道题不需要考虑的很复杂
 * 其实我觉得给C语言实训考试都能做了
 * 没有什么高深的动态规划的实现
 * 不需要考虑什么动态转移方程
 * 直接一个遍历
 * 计数
 * 数一下有几个上升下降的转换点
 * 连续上升和连续下降的若干个数据看作一个
 * 计数器的结果就是答案
 */

int main() {
	int number;
	scanf("%d",&number);

	if (number==1) {
		// 如果只有1个数字，按照定义就是1
		printf("1\n");
	} else {
		// 如果不止一个数字，显然不论如何结果一定≥1
		int cnt = 1;
		int a[MAXN]= {0};

		for (int i=0; i<number; ++i) scanf("%d",&a[i]);

		// 跳过最开始的若干个相同的值（如果存在）
		int idx=1;

		while (idx < number && a[idx]==a[idx-1]) idx++;

		// 此时如果等于总个数（即所有数都相等），答案就是-1，否则就要开始判断
		if (idx!=number) {
			// 首先获得第一组趋势，是上升还是下降，并置cnt为2
			int tendency = a[idx]-a[idx-1];
			cnt = 2;

			// 遍历剩下的每一个数，步长为1
			for (int i=idx+1; i<number; ++i) {
				// 求出是变大了还是变小了
				int delta = a[i]-a[i-1];

				if (delta*tendency>0) {
					// 如果相乘大于0，说明变化趋势相同，忽略，继续比下一个
				} else if (delta*tendency<0) {
					// 如果异号，说明满足ZigZag，计数器加1，反转趋势
					cnt++;
					tendency = -tendency;
				} else {
					// 如果相等就继续往后
				}
			}
		}

		// 输出计数器的值
		printf("%d\n",cnt);
	}

	return 0;
}
