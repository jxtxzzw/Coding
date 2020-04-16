#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define N 1000
 
int isZero(char * st){
for (int i=0;i<strlen(st);i++){
	if (st[i]!='0') return 0;
}
return 1;
}
 
void divide(char * st){
	for (int i=0;i<strlen(st)-1;i++){
		int tmp = st[i]-'0';
		st[i]=tmp/2+'0';
		st[i+1]=tmp%2*10+st[i+1];
	}
	int tmp = st[strlen(st)-1]-'0';
	st[strlen(st)-1]=tmp/2+'0';
}
 
void add(char * ans,char*buffer){
int pos = 0;
while (pos<strlen(buffer)){
	int tmp = buffer[strlen(buffer)-1-pos]-'0';
	ans[N-pos]+=tmp;
	pos++;
}
pos = 0;
while (pos<strlen(ans)){
while (ans[N-pos]>'9'){
		ans[N-pos]-=10;
		ans[N-1-pos]+=1;
};
pos++;
}
}
 
int main() {
    int cas;
    scanf ( "%d", &cas );
    for ( int t = 0; t < cas; t++ ) {
        char st[N+2]={0};
        char bin[N+2]={0};
        char ans[N+2]={0};
        for (int i=0;i<N+1;i++){
			ans[i]='0';
        }
        scanf("%s",st);
		while (!isZero(st)){
			bin[strlen(bin)]=(st[strlen(st)-1]-'0')%2+'0';
			divide(st);
		}
		for (int i=0;i<strlen(bin);i++){
			char buffer[N]={0};
			int p=strlen(bin)-1-i;
			sprintf(buffer,"%.0f",pow(2,p)*(bin[i]-'0'));
			add(ans,buffer);
		}
        int beg=0;
		while (ans[beg]=='0'){
			beg++;
		}
		if (beg==strlen(ans)){
			beg--;
		}
		printf("case #%d:\n%s\n",t,(ans+beg));
    }
    return 0;
}