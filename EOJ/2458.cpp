#include <stdio.h>
#include <iostream>
#define MAXN 100000

using namespace std;

typedef struct {
	int index;
	int left;
	int right;
	int frequentNumber;
	int frequency;
	int rightSerial;
	int leftEndPoint;
	int rightEndPoint;
	int leftSerial;
} NODE;

NODE data[MAXN * 4];
void maintain(NODE* node) {
	NODE* leftChild = &data[node->index << 1];
	NODE* rightChild = &data[node->index << 1 | 1];
	node->leftEndPoint = leftChild->leftEndPoint;
	node->rightEndPoint = rightChild->rightEndPoint;
	int leftSerial = leftChild->leftSerial;
	int rightSerial = rightChild->rightSerial;

	if (leftChild->rightEndPoint == rightChild->leftEndPoint) {
		if (leftSerial == leftChild->right - leftChild->left + 1) {
			leftSerial += rightChild->leftSerial;
		}

		if (rightSerial == rightChild->right - rightChild->left + 1) {
			rightSerial += leftChild->rightSerial;
		}
	}

	node->leftSerial = leftSerial;
	node->rightSerial = rightSerial;
	int leftFrequency = leftChild->frequency;
	int rightFrequency = rightChild->frequency;

	if (leftChild->rightEndPoint == rightChild->leftEndPoint) {
		int crossFrequency = leftChild->rightSerial + rightChild->leftSerial;

		if (leftFrequency > rightFrequency) {
			if (leftFrequency > crossFrequency) {
				node->frequency = (leftFrequency);
				node->frequentNumber = (leftChild->frequentNumber);
			} else {
				node->frequency = (crossFrequency);
				node->frequentNumber = (leftChild->rightEndPoint);
			}
		} else {
			if (rightFrequency > crossFrequency) {
				node->frequency = (rightFrequency);
				node->frequentNumber = (rightChild->frequentNumber);
			} else {
				node->frequency = (crossFrequency);
				node->frequentNumber = (leftChild->rightEndPoint);
			}
		}
	} else {
		if (leftFrequency > rightFrequency) {
			node->frequency = (leftFrequency);
			node->frequentNumber = (leftChild->frequentNumber);
		} else {
			node->frequency = (rightFrequency);
			node->frequentNumber = (rightChild->frequentNumber);
		}
	}
}

void build(int nodeIndex, int left, int right) {
	NODE *node = &data[nodeIndex];

	if (left == right) {
		node->index = nodeIndex;
		node->left = left;
		node->right = right;
		node->frequency = 1;
		node->leftSerial = right - left + 1;
		node->rightSerial = right - left + 1;
		int x;
		scanf("%d", &x);
		node->frequentNumber = x;
		node->leftEndPoint = x;
		node->rightEndPoint = x;
	} else {
		build(nodeIndex * 2, left, (left + right) >> 1);
		build(nodeIndex * 2 + 1, ((left + right) >> 1) + 1, right);
		node->index = nodeIndex;
		node->left = left;
		node->right = right;
		node->frequency = 0;
		maintain(node);
	}
}

int query(int from, int to, int index) {
	NODE* node = &data[index];

	if (from <= node->left && to >= node->right) {
		return node->frequency;
	}

	int mid = (node->left + node->right) >> 1;

	if (to <= mid) {
		return (query(from, to, node->index * 2));
	} else if (from > mid) {
		return (query(from, to, (node->index * 2) + 1));
	} else {
		int leftFrequency = (query(from, to, node->index * 2));
		int rightFrequecy = (query(from, to, (node->index * 2) + 1));
		int crossFrequency = 0;

		if (data[(node->index) * 2].rightEndPoint
				== data[(node->index) * 2 + 1].leftEndPoint) {
			int crossFrom = max(from,
					mid + 1 - data[(node->index) * 2].rightSerial);
			int crossTo = min(to, mid + data[(node->index) * 2 + 1].leftSerial);
			crossFrequency = crossTo - crossFrom + 1;
		}

		return max(max(leftFrequency, rightFrequecy), crossFrequency);
	}
}

int main() {
	int numbers;

	while (scanf("%d", &numbers) == 1 && numbers != 0) {
		int queries;
		scanf("%d", &queries);
		build(1, 1, numbers);
		int from = 0;
		int to = 0;

		for (int i = 0; i < queries; ++i) {
			scanf("%d%d", &from, &to);
			printf("%d\n", query(from, to, 1));
		}
	}
}

