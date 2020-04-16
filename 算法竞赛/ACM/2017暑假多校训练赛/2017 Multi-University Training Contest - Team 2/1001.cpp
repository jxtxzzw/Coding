//by - QDW
#include <bits/stdc++.h>
using namespace std;
int f(int a,int b)
{
    if(a>b) return a-b;
    else return b-a;
}
int main()
{
    int N,X,Y;
    int cas,ss;
    scanf("%d",&cas);
    for(ss=0;ss<cas;ss++)
    {
        scanf("%d%d%d\n",&N,&X,&Y);
        char a[N],b[N];
        gets(a);gets(b);
        int dif=0,i,j,k;
        for(i=0;i<N;i++)
        {
            if(a[i]!=b[i])
            {
                dif++;
            }
        }
        int n1,n2;
        n1=N-X;n2=N-Y;
        int tt=f(X,Y);int kk=-1;
        if(dif>=tt)
        {
                if((X+Y)<=(2*N-dif))
                    kk=1;
        }
        if(kk==-1)
            printf("Lying\n");
        else printf("Not lying\n");
    }
    return 0;
}