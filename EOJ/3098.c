#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
int main() {
    char st1[81],st2[81];
    scanf("%s%s",st1,st2);
    if (strlen(st1)>strlen(st2)) {
        for (int i=0; i<strlen(st2); i++) {
            printf("%c%c",st1[i],st2[i]);
        }
        for (int i=strlen(st2); i<strlen(st1); i++) {
            printf("%c",st1[i]);
        }
 
    } else {
        for (int i=0; i<strlen(st1); i++) {
            printf("%c%c",st1[i],st2[i]);
        }
        for (int i=strlen(st1); i<strlen(st2); i++) {
            printf("%c",st2[i]);
        }
 
 
    }
    return 0;
}