//by - qdw
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n;
    scanf("%d",&n);
    int i;
    int a[n];
    for(i=0;i<n;i++)
    {
        scanf("%d",&a[i]);
    }
    int re=0;
    for(i=0;i<n;i++)
        if(a[i]<=35) re++;
    printf("%d\n",re);
    return 0;
}