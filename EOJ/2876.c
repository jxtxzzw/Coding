#include <stdio.h>

int countBinaryOne(int num)
{
    int ret = 0;
    while (num>0){
        ret += num%2;
        num/=2;
    }
    return ret;
}

int main(){
    int cas;
    scanf("%d",&cas);
    for (int t=0;t<cas;t++){
        int a,b;
        scanf("%d%d",&a,&b);
        printf("%d\n",countBinaryOne(a^b));
    }
    return 0;
}
