#include <iostream>
#include <stdio.h>

using namespace std;

int main() {
	int cas;
	scanf("%d",&cas);

	while (cas--) {
		int len,num;
		scanf("%d%d",&len,&num);
		int maxTime = 0, minTime = 0;

		for (int i=0; i<num; i++) {
			int pos;
			scanf("%d",&pos);
			minTime = max(minTime,min(pos,len-pos));
			maxTime = max(maxTime,max(pos,len-pos));
		}

		printf("%d %d\n",minTime,maxTime);
	}
}
