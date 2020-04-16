#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(){

		int caseNumber = 0;
		scanf("%d",&caseNumber);
		while (caseNumber--) {
			int number = 0;
			int sum = 0;
			scanf("%d",&number);
			for (int i = 0; i < number; ++i) {
				int x;
				scanf("%d",&x);
				sum += x;
			}
			printf("%d\n",sum * (int)pow(2,number-1));

		}
	}

