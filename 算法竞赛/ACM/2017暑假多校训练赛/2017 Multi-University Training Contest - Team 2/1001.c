//by - LTY & PZW
#include<stdio.h>
#include<string.h>
#include <stdlib.h>

char s1[80005];
char s2[80005];


int main()
{
    //freopen("input.txt","r",stdin);
    int cas=0;
    scanf("%d",&cas);
    for (int t=0 ; t<cas ; t++)
    {
        memset(s1,0,sizeof(s1));
        memset(s2,0,sizeof(s2));
        int n,x,y;
        scanf("%d%d%d",&n,&x,&y);
        scanf("%s%s",s1,s2);
        int deltaxy=abs(x-y);
        int deltastr=0;
        int w=x+y,l=0;
        for (int i=0 ; i<n ; i++) {if (s1[i]!=s2[i]) {deltastr++;l++;}
        else l=l+2;}
        if (deltastr<deltaxy) puts("Lying");
        else if(w>l)puts("Lying");
        else puts("Not lying");
    }
    return 0;
}