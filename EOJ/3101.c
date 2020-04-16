#include <stdio.h>
#define N 10

void transpose(int (*m)[N], int n) {
    for (int i=0; i<n; i++) {
        for (int j=i; j<n; j++) {
            int tmp=*(m[i]+j);
            *(m[i]+j)=*(m[j]+i);
            *(m[j]+i)=tmp;
        }
    }
}

int main() {
    int m[N][N],i,j,n;
    scanf("%d",&n);
    for(i=0; i<n; i++)
        for(j=0; j<n; j++)
            scanf("%d",&m[i][j]);
    transpose(m,n);
    for(i=0; i<n; i++)
        for(j=0; j<n; j++) printf("%d%c",m[i][j],j<n-1?' ':'\n');
    return 0;
}
