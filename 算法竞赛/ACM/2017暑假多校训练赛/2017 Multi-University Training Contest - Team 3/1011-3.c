//by -qdw
//optimized by -zzw
#include <stdio.h>
#include <stdlib.h>

int main()
{

    scanf("%*d");

    int a;int re=0;
   while (scanf("%d",&a)!=EOF)
    {

         if(a<=35) re++;
    }
    printf("%d\n",re);
    return 0;
}