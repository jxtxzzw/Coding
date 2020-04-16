#include <stdio.h>
#define MAXN 107
#define max(a,b) a>b?a:b

int main()
{
    int caseNumber;
    scanf("%d",&caseNumber);
    while (caseNumber-- != 0)
    {
        int data[MAXN]= {0};
        int dp[MAXN]= {0};
        int length;
        scanf("%d",&length);
        for (int i = 0; i < length; ++i)
        {
            scanf("%d",&data[i]);
        }
        dp[0] = data[0];
        int max = dp[0];
        for (int i = 1; i < length; ++i)
        {
            if (dp[i - 1] < 0)
            {
                dp[i] = data[i];
            }
            else
            {
                dp[i] = dp[i - 1] + data[i];
            }
            max = max(max, dp[i]);
        }
        printf("%d\n",max);
    }
}


/*
用在线处理的思想
从数组的第1个元素开始遍历
dp[]记录从最开始到第i个的连续子序列和
对于第i个元素
如果dp[i-1]已经小于0了那么加上data[i]一定会变小
所以就dp[i]就直接是data[i]
但是如果dp[i-1]大于等于0那么加上data[i]会使得dp[i]变大
于是dp[i]=dp[i-1]+data[i]
*/
