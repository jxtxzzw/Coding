#include <stdio.h>
#include <string.h>


void toBinaryString(char * bin,int num)
{
    int pos = 0;
    while (num>0){
        bin[pos++] = num%2+'0';
        num/=2;
    }
}

int main(){
    int cas;
    scanf("%d",&cas);
    for (int t=0;t<cas;t++){
        int num;
        scanf("%d",&num);
        char bin[100000]={0};
        toBinaryString(bin,num);
        int cnt,max;
        max=cnt=1;
        for (int i=1;i<strlen(bin);i++){
            if (bin[i-1]!=bin[i]){
                cnt++;
            } else {
                if (cnt>max) {max =cnt;}
                cnt=1;
            }
        }
          if (cnt>max) {max =cnt;}
        printf("case #%d:\n%d\n",t,max);
    }
    return 0;
}
