/***************************************************************/
/*                                                             */
/*  DON'T MODIFY main function ANYWAY!                         */
/*                                                             */
/***************************************************************/

#include <stdio.h>

//********** Specification of i2a **********
void i2a(char s[],long long int n)
/* PreCondition:
        n is a non-negative integer, s is a character buffer
   PostCondition:
        put string representation of n in s
*/

{ //TODO: your function definition -- RECURSIVE SOLUTION
 sprintf(s,"%I64d",n);
 return;

}

/***************************************************************/

int main()
{  long long int n;
   char s[20];
   scanf("%I64d",&n);

//********** i2a is called here ********************
   i2a(s,n);
//**************************************************
   printf("%s\n",s);

   return 0;
}
