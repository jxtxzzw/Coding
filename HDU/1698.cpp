#include <iostream>
#include <stdio.h>
#include <memory.h>

#define MAXN 100007

using namespace std;

typedef struct {
	int index;
	int left;
	int right;
	int sum;
	int targetValue;
} NODE;

NODE data[MAXN * 4];

void initial(NODE* node, int nodeIndex, int left, int right, int sum);
void build(int nodeIndex, int left, int right);
int isPrimarySegment(int left, int right);
int getChildrenValueSum(NODE* node);
void modify(int from, int to, int targetValue, int index);
void pullDownLazy(NODE* node) ;
int query(int from, int to, int index);
int getValue(NODE* node) ;
int isCompletelyCovered(int from, int to, int left, int right);

int main() {
	//freopen("data.in","r",stdin);
	int cas;
	scanf("%d",&cas);

	for (int t=1; t<=cas; t++) {
		memset(data,0,sizeof(data));
		int numbers,queries;
		scanf("%d%d",&numbers,&queries);
		build(1,1,numbers);
		int from,to;
		int targetValue = 0;

		for (int i = 0; i < queries; ++i) {
			scanf("%d%d%d",&from,&to,&targetValue);
			modify(from, to, targetValue, 1);
		}

		printf("Case %d: The total value of the hook is %d.\n",t,query(1, numbers, 1));
	}
}


int isPrimarySegment(int left, int right) {
	return left == right;
}

void build(int nodeIndex, int left, int right) {
	NODE* node = &data[nodeIndex];

	if (isPrimarySegment(left, right)) {
		initial(node,nodeIndex, left, right, 1);
	} else {
		build(nodeIndex * 2, left, (left + right) / 2);
		build(nodeIndex * 2 + 1, ((left + right) / 2) + 1, right);
		initial(node,nodeIndex, left, right, 0);
		node->sum = getChildrenValueSum(node);
	}
}

void initial(NODE* node, int nodeIndex, int left, int right, int sum) {
	node->index = nodeIndex;
	node->left = left;
	node->right = right;
	node->sum= sum;
}

int getChildrenValueSum(NODE* node) {
	return getValue(&data[(node->index)*2]) + getValue(&data[(node->index)*2+1]);
}

void modify(int from, int to, int targetValue, int index) {
	NODE* node = &data[index];

	if (isCompletelyCovered(from, to,node->left, node->right)) {
		node->targetValue = targetValue;
		return;
	}

	pullDownLazy(node);
	int mid =  (node->left + node->right) /2;

	if (to > mid) {
		modify(from, to, targetValue, (node->index) *2 +1);
	}

	if (from < mid + 1) {
		modify(from, to, targetValue, (node->index) *2);
	}

	node->sum=getChildrenValueSum(node);
}

void pullDownLazy(NODE* node) {
	if (node->targetValue!=0) {
		NODE* leftChild = &data[(node->index) * 2];
		leftChild->targetValue = node->targetValue;
		NODE* rightChild = &data[(node->index) * 2+1];
		rightChild->targetValue = node->targetValue;
		node->targetValue = 0;
	}
}
int query(int from, int to, int index) {
	NODE* node = &data[index];

	if (isCompletelyCovered(from, to,node->left, node->right)) {
		return getValue(node);
	}

	pullDownLazy(node);
	node->sum=getChildrenValueSum(node);
	int mid =  (node->left + node->right) /2;

	if (to <= mid) {
		return query(from, to, (node->index)*2);
	} else if (from > mid) {
		return query(from, to, (node->index)*2+1);
	} else {
		return query(from, to, (node->index)*2) + query(from, to, (node->index)*2+1);
	}
}

int getValue(NODE* node) {
	if (node->targetValue != 0) {
		node->sum = node->targetValue * ((node->right) - (node->left) +1);
	}

	return node->sum;
}

int isCompletelyCovered(int from, int to, int left, int right) {
	return from <= left && to >= right;
}

