#include <bits/stdc++.h>
#define MAX_N 100007

using namespace std;

long long aa[MAX_N];

int main()
{
#ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
#endif // ONLINE_JUDGE
    int n;
    scanf("%d",&n);
    long long delta;
    long long a,b,c,d,e;
    long long ans;
    switch (n)
    {
    default:
        for (int i=0; i<n; ++i) scanf("%lld",&aa[i]);
        long long deltas[30][2];
        int cur = 0;
        if(n>=9)
        {
            for (int i=1; i<n; ++i)
            {
                long long t = aa[i]-aa[i-1];
                int j;
                for (j=0; j<cur; ++j)
                {
                    if (deltas[j][0]==t)
                    {
                        deltas[j][1]++;
                        break;
                    }
                }
                if (j==cur)
                {
                    deltas[cur][0] = t;
                    deltas[cur][1] = 1;
                    cur++;
                }
            }
            int maxIndex = 0;
            for (int i=0; i<cur; ++i)
            {
                if (deltas[i][1]>deltas[maxIndex][1])
                {
                    maxIndex = i;
                }
            }
            delta = deltas[maxIndex][0];
            if (aa[1]-aa[0]!=delta)
            {
                int beg=1;
                for (beg = 1; beg < n; ++beg)
                {
                    if (aa[beg]-aa[beg-1]==delta)
                    {
                        break;
                    }
                }
                for (int i=0; i<n; ++i)
                {
                    long long w = aa[beg]-(beg-i)*delta;
                    printf("%lld ",w);
                }




            }
            else
            {
                printf("%d %d ",aa[0],aa[1]);
                long long x = aa[1];
                for (int i=2; i<n; ++i)
                {
                    x += delta;
                    printf("%lld ",x);
                }
            }
        }
        else
        {

            for (int i=0; i<n; ++i)
            {
                for (int j = i+1; j<n; ++j)
                {
                    long long t = (aa[j]-aa[i]) / (j-i);
                    int k=0;
                    for (k=0; k<cur; ++k)
                    {
                        if (deltas[k][0]==t)
                        {
                            deltas[k][1]++;
                            break;
                        }
                    }
                    if (k==cur)
                    {
                        deltas[cur][0] = t;
                        deltas[cur][1] = 1;
                        cur++;
                    }

            }
        }
        int maxIndex = 0;
        for (int i=0; i<cur; ++i)
        {
            if (deltas[i][1]>deltas[maxIndex][1])
            {
                maxIndex = i;
            }
        }
        delta = deltas[maxIndex][0];
        long long bb[30]= {0};
        for (int ii=0; ii<n; ++ii)
        {
            for (int i=0; i<n; ++i)
            {
                bb[i] = aa[ii] + ((i-ii)*delta);
            }
            int cnt = 0;
            for (int i=0; i<n; ++i)
            {
                if (aa[i]!=bb[i]) cnt++;
                if (cnt > 3) break;
                if (cnt > 3) break;
            }
            if (cnt<=3)
            {
                for (int i=0; i<n; ++i) printf("%lld ",bb[i]);
                return 0;
            }
        }



    }
}
return 0;

}
