/***************************************************************/
/*                                                             */
/*  DON'T MODIFY main function ANYWAY!                         */
/*                                                             */
/***************************************************************/
 
#include <stdio.h>
#include <string.h>
 
int count(char s[],char t[])
  /* precondition: s和t是两个字符串，t不会是空串，且t不会重叠
     postcondition: 返回t在s中出现的次数
  */
 
{ //TODO: your function definition
    int i=0,j=0;
    int ret=0;
    while(i<strlen(s)){
        while (s[i]==t[j] && i<strlen(s) && j<strlen(t)){
            i++;
            j++;
        }
        if (j==strlen(t)){
            ret++;
            i--;
        }
        j=0;
        i++;
    }
 
 
 
return ret;
}
 
/***************************************************************/
#define N 80
int main()
{   char s[N+1],t[N+1];
    scanf("%s%s",s,t);
 
//********** count is called here ******************
    printf("%d\n",count(s,t));
//**************************************************
 
    return 0;
}