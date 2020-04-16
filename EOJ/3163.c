#include <stdio.h>
#include <stdlib.h>
int gcd(int a, int b) {
    if (b==0) {
        return a;
    } else {
        return gcd(b,a%b);
    }
}
 
int main() {
    unsigned int num;
    int i=0,count=0,a[100]= {0};
    scanf("%ud",&num);
    while (num>0) {
        a[i]= num % 2;
        num = num / 2;
        i++;
    }
    num=i;
    while (i>=0) {
        if (a[i]==1) count++;
        i--;
    }
 
    if (count==0) {
        printf("%d,%d:%d\n",0,0,32);
    } else {
        int div =gcd (count,32);
        printf("%d,%d:%d\n",count,count/div,32/div);
    }
    return 0;
}