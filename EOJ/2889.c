#include <stdio.h>
#include <stdlib.h>

int n;
long long k,i,j;

int main(){

  while (scanf("%d",&n)!=EOF){
	long long a[100][100]={0};
	long long pos[10001][3]={0};
  a[1][(n+1) / 2]=1;
  pos[1][1]=1;
  pos[1][2]=(n+1) / 2;
  for (k=2;k<=n*n;k++){

   if ((pos[k-1][1]==1) && (pos[k-1][2]!=n))
     {
       a[n][pos[k-1][2]+1]=k;
       pos[k][1]=n;
       pos[k][2]=pos[k-1][2]+1;
       continue;
     }

     if ((pos[k-1][2]==n) && (pos[k-1][1]!=1))
       {
         a[pos[k-1][1]-1][1]=k;
         pos[k][1]=pos[k-1][1]-1;
         pos[k][2]=1;
         continue;
       }

     if ((pos[k-1][1]==1) && (pos[k-1][2]==n)) {
         a[2][n]=k;
         pos[k][1]=2;
         pos[k][2]=n;
         continue;
       }

    if ((pos[k-1][1]!=1) && (pos[k-1][2]!=n))
      {        if (a[pos[k-1][1]-1][pos[k-1][2]+1]==0)
          {
             a[pos[k-1][1]-1][pos[k-1][2]+1]=k;
             pos[k][1]=pos[k-1][1]-1;
             pos[k][2]=pos[k-1][2]+1;
          }
          else
          {
             a[pos[k-1][1]+1][pos[k-1][2]]=k;
             pos[k][1]=pos[k-1][1]+1;
             pos[k][2]=pos[k-1][2];
          }
      }

 }

 for (i=n;i>=1;i--) {
  for (j=n;j>=1;j--)
    {
    printf("%lld",a[i][j]);
    if (j!=1) printf(" "); else printf("\n");
  }
}
}
}