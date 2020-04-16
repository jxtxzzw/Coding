#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define N 1000
int main(){
    int cas;
    scanf("%d",&cas);
    for (int t=0;t<cas;t++){
        char st1[N+1]={0},st2[N+1]={0};
        scanf("%s%s",st1,st2);
        int ans[N+1]={0};
        for (int i=0;i<strlen(st1);i++){
            char ch = st1[strlen(st1)-1-i];
            if (ch>='0' && ch <='9'){
                ch=ch-'0';
            } else {
                ch=ch-'A'+10;
            }
            ans[N-i]+=ch;
        }
        for (int i=0;i<strlen(st2);i++){
            char ch = st2[strlen(st2)-1-i];
            if (ch>='0' && ch <='9'){
                ch=ch-'0';
            } else {
                ch=ch-'A'+10;
            }
            ans[N-i]+=ch;
        }
        for (int i=N;i>=0;i--){
            if (ans[i]>=16){
                ans[i]-=16;
                ans[i-1]+=1;
            }
        }
        printf("case #%d:\n",t);
        int pos = 0;
        while (ans[pos++]==0 && pos<=N);
        for (int i=pos-1;i<=N;i++){
            printf("%X",ans[i]);
        }
        printf("\n");
    }
    return 0;
}
