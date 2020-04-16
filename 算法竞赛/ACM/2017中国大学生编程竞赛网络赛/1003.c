// By - Dongwei Qian
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
char a[3010][3010];
int main()
{
    int cas,ss;
    scanf("%d",&cas);
    for(ss=0;ss<cas;ss++)
    {
        int n;
        scanf("%d",&n);
        int i,j,k;
        for(i=0;i<3010;i++)
        {
            for(j=0;j<3010;j++)
                a[i][j]=0;
        }
        for(i=1;i<n;i++)
        {
            for(j=i+1;j<=n;j++)
            {
                int temp;scanf("%d",&temp);
                a[i][j]=temp;
            }
        }
        int kk=-1;
        if(n<3)
            kk=-1;
        else
        {
            for(i=1;i<=n-2;i++)
            {
                if(kk==1) break;
                for(j=i+1;j<=n-1;j++)
                {
                    if(kk==1) break;
                    for(k=j+1;k<=n;k++)
                    {
                        int temp=a[i][j]+a[i][k]+a[j][k];
                        if(temp==3||temp==0)
                            {
                                kk=1;break;
                            }
                    }
                }
            }
        }
        if(kk==1)
        {
            printf("Bad Team!\n");
        }
        else
            printf("Great Team!\n");
    }
    return 0;
}