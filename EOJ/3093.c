#include<stdio.h>
#include<stdlib.h>

int gcd(int a,int b){
if (b==0){
return a;
} else {
return gcd(b,a%b);
}
}


int main(){
    int a,b,c;
scanf("%d%d%d",&a,&b,&c);
printf("%d",gcd(gcd(a,b),c));
return 0;
}
