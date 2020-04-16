//by -qdw
//optimized by - zzw
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n;
    scanf("%d",&n);
    int i;
    int a;int re=0;
    for(i=0;i<n;i++)
    {
        scanf("%d",&a);
         if(a<=35) re++;
    }
    printf("%d\n",re);
    return 0;
}