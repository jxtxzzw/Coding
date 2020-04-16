#include <stdio.h>
#include <stdlib.h>

int comp(int * dataArrayA, int capacityOfA, int * dataArrayB, int capacityOfB);
/**
 * Precondition:
 * dataArrayA - 线性表A
 * capacityOfA - 线性表A的节点个数
 * dataArrayB - 线性表B
 * capacityOfB - 线性表B的节点个数
 *
 * Postcondition：
 * 如果A<B，返回-1，如果A=B，返回0，如果A>B，返回1
 *
 */

int main() {
	// 线性表A和B的节点个数
	int capacityOfA;
	int capacityOfB;
	scanf("%d%d", &capacityOfA, &capacityOfB);
	// 读入两个线性表
	int dataArrayA[1007] = { 0 };
	int dataArrayB[1007] = { 0 };
	for (int i = 0; i < capacityOfA; ++i)
		scanf("%d", &dataArrayA[i]);
	for (int i = 0; i < capacityOfB; ++i)
		scanf("%d", &dataArrayB[i]);
	// 输出结果
	printf("%d\n", comp(dataArrayA, capacityOfA, dataArrayB, capacityOfB));
	return 0;
}

int comp(int * dataArrayA, int capacityOfA, int * dataArrayB, int capacityOfB) {
	// 取两者中较小的个数为比较长度
	int commonLength = capacityOfA < capacityOfB ? capacityOfA : capacityOfB;
	// 遍历A和B的第i个元素
	for (int i = 0; i < commonLength; ++i)
		if (dataArrayA[i] != dataArrayB[i])
			return dataArrayA[i] < dataArrayB[i] ? -1 : 1;
	// 如果这两个元素不相等，根据题意比较大小，根据比较后的分别返回1或者-1

	return capacityOfA == capacityOfB ? 0 : capacityOfA < capacityOfB ? -1 : 1;
	// 如果比完了整个commonLength，就要比较A和B的节点个数，根据比较后的结果返回1、-1或0
}
