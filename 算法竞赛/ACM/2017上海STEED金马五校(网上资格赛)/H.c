#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>

int cmp(const void * a,const void * b)
{
    return *(int*)a-*(int*)b;
}


int main()
{
    int n;
    while (scanf("%d",&n)!=EOF)
    {
        int num[105]={0};
        for (int i=0;i<n;i++)
        {
            scanf("%d",&num[i]);
        }
        qsort(num,n,sizeof(int),cmp);
        for (int i=0;i+1<n;i++)
        {
            int tmp=(num[i]+num[i+1])/2;
            num[i+1]=tmp;
        }
        printf("%d\n",num[n-1]);
    }






    return 0;
}