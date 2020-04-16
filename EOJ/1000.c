#include <stdio.h>
#include <stdlib.h>

int main()
{
int f=1;
while (f) {
	int a,b;
	scanf("%d %d",&a,&b);
	printf("%d\n",a+b);
	char c=getchar();
if (c==EOF) f=0;
}
	return 0;
}
