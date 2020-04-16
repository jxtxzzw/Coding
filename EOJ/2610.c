#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXN 1000
int main(int argc, char* argv[])
{
int n;
int result[MAXN];
memset(result, 0, sizeof(int)*MAXN);
scanf("%d", &n);
for (int t=0; t<n; t++) {
int k,m;
scanf("%d%d", &m, &k);
int sum=0;
for (int j=0; j<m; j++) {
int temp;
scanf("%d", &temp);
if (j<k) {
sum += temp;
}
}
result[t] = sum;
}
for (int i=0; i<n; i++) {
printf("%d\n", result[i]);
}
return 0;
}
