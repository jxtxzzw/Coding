#include <stdio.h>
#include <string.h>
#include <iostream>

using namespace std;

int main()
{
    char ss[1000]={0};
    scanf("%s",ss);

    int n;
    scanf("%d",&n);
    while (n--){
        char s[1000]={0};
        int st,ed;
        scanf("%d%d",&st,&ed);
        strncpy(s,ss+st,ed-st+1);
        char sub[1000]={0};
        scanf("%s",sub);
        int counter = 0;
        char* offset = s;
        while (strstr(offset,sub)){
            ++counter;
            offset = strstr(offset,sub) + 1;
        }
        printf("%d\n",counter);
    }

    return 0;
}
