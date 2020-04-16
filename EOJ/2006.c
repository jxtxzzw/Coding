#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#define N 1000000
int i;
int sub(int x) {
    int ret=0;
    while (x>0) {
        ret+=x%10;
        x/=10;
    }
    return ret;
}
int main() {
    int *a=(int *)malloc(N*sizeof(int));
    for (i=1; i<=N; i++) {
        a[i+sub(i)]=1;
    }
    for (i=1; i<=N; i++) {
        if (a[i]==0) printf("%d\n",i);
    }
    return 0;
}