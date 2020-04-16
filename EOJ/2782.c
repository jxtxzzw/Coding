#include <stdio.h>
#include <stdlib.h>

int main() {
int n,i;
char st[11];
scanf("%d\n",&n);
for (i=0;i<n;i++){
gets(st);
switch (st[0]){
case 'E': {printf("Excellent\n");break;}
case 'C': {printf("Cheer\n");break;}
case 'N': {printf("Nice\n");break;}
case 'U': {printf("Ultimate\n");break;}
case 'I': {printf("I'm possible\n");break;}
case 'A': {printf("Accept More\n");break;}
}
}
return 0;
}
