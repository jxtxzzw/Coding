#include <stdio.h>

int main(){

int n,k,x;
scanf("%d%d%d",&n,&k,&x);
int a[107]={0};
int sum =0;
for (int i=0;i<n-k;++i){
    int num;
    scanf("%d",&num);
    sum+=num;
}
sum+=(k*x);
printf("%d\n",sum);
}
