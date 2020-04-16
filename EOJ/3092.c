#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main(){
int n,r;
scanf("%d%d",&n,&r);
printf("%.2f",pow(1+r/100.0,n));
return 0;
}
