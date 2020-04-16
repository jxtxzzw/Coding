#include <bits/stdc++.h>
using namespace std;
int main()
{
    long long n,k;int nn=1;
    while(scanf("%lld%lld",&n,&k)!=EOF)
    {
        if(k<=n)
        {
            printf("Case #%d: %d\n",nn,k);
        }
        else
        {
            int tem=(k-n)%(2*n-2);
            if(tem==0)
            {
                printf("Case #%d: %d\n",nn,n);
            }
            else if(tem<=n-1)
            {
                printf("Case #%d: %d\n",nn,tem);
            }
            else
            {
                tem=tem-n+1;
                printf("Case #%d: %d\n",nn,tem);
            }
        }
        nn++;
    }

    return 0;
}