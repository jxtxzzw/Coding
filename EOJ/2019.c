
#include <stdio.h>
#include <math.h>

int main() {
int i;
unsigned num,low,high;

while (scanf("%u",&num)!=EOF){
high=num%(int)pow(2,16);
low=num/(int)pow(2,16);
//low^=(int)(pow(2,16)-1);
low^=0xFFFF;
high^=low;
printf("%X\n",high*(int)pow(2,16)+low);
}
return 0;
}
