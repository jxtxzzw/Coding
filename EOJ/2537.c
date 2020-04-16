#include <stdio.h>
int main() {
    int n,i,cntcha,cntnum,cntoth;
    char ch;
    scanf("%d\n",&n);
    for (i=0; i<n; i++) {
        cntcha=0,cntnum=0,cntoth=0;
        while ((ch=getchar()) && (ch!='\n')) {
            if ((ch>='A' && ch<='Z') || (ch>='a' && ch<='z')) {
                cntcha++;
            } else if (ch>='0' && ch<='9') {
                cntnum++;
            } else cntoth++;
        }
        printf("character:%d\nnumber:%d\nothers:%d\n",cntcha,cntnum,cntoth);
    }
    return 0;
}
