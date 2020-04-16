#define N 10
#include <stdio.h>

void multiply(int (*A)[N], int (*B)[N], int (*C)[N],int n) {
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            C[i][j]=0;
            for (int k=0; k<n; k++) {
                C[i][j]+=A[i][k]*B[k][j];
            }
        }
    }
}


int main() {
    int A[N][N],B[N][N],C[N][N],n,i,j;
    scanf("%d",&n);
    for (i=0; i<n; i++)
        for (j=0; j<n; j++)
            scanf("%d",&A[i][j]);
    for (i=0; i<n; i++)
        for (j=0; j<n; j++)
            scanf("%d",&B[i][j]);
    multiply(A,B,C,n);
    for (i=0; i<n; i++)
        for (j=0; j<n; j++)
            printf("%d%c",C[i][j],j<n-1?' ':'\n');
    return 0;
}
