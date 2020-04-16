/***************************************************************/
/*                                                             */
/*  DON'T MODIFY main function ANYWAY!                         */
/*                                                             */
/***************************************************************/
 
#include <stdio.h>
#include <math.h>
#include <string.h>
 
unsigned b2i(char s[])
  /* PreCondition:  s是由0和1组成的字符串，且字符串长度不超32
      PostCondition: 返回与字符串s对应的十进制数
  */
 
{ //TODO: your function definition
 unsigned int sum=0;
 
 for (int i=strlen(s)-1;i>=0;i--){
        sum+=(int)(pow(2,(strlen(s)-1)-i)*(s[i]-'0'));
 
 }
  return sum;
 
 
 
}
 
/***************************************************************/
#define N 32
int main()
{   char s[N+1];
    scanf("%s",s);
 
//********** b2i is called here ********************
    printf("%u\n",b2i(s));
//**************************************************
 
    return 0;
}