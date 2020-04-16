#include<stdio.h>

int main() {
	int i,j,n;
	int e[10001]= {0};
	scanf("%d",&n);
	i=2;
	while (i<=n)	{
		e[i]=1;
		i++;
	}
	i=2;
	while (i<=n)	{
		if (e[i]!=0) {
			j=i;
			while (j*i<=n) {
				e[j*i]=0;
				j++;
			}
		}
		i++;
	}
	printf("2");
	i=3;
	while (i<=n) {
		if (e[i]!=0) {
			printf(" %d",i);
		}
			i++;
	}
	printf("\n");
	return 0;
}
