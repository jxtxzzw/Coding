#include <stdio.h>
int main()
{
    int n;
    scanf("%d",&n);
    long x = 0;
    long y = -10;
    long z = 0;
    while (n--)
    {
        int p,q,r;
        scanf("%d%d%d",&p,&q,&r);
        x+=p;
        y+=q;
        z+=r;
    }
    printf("%s\n",x == 0 && y == 0 && z == 0 ? "NO" : "YES");
}
