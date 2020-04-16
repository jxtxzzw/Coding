#include <stdio.h>
#include <stdlib.h>
 
double num[1000]={0},data[2][1000]={0};
int isnum[1000]={0};
double tmp=0.0;
int p=0,isdot=0,dotnum=0,cnt=0,sig=1;
char ch;
 
double pop() {
    double ret=num[p];
    p--;
    return ret;
}
 
void push(double x) {
    p++;
    num[p]=x;
    return;
}
 
void getarray(void) {
 
    do {
		ch=getchar();
        if ((ch=='+')||(ch=='-')||(ch=='*')||(ch=='/')) {
            if (ch !='-'){
            	isnum[cnt]=0;
				data[isnum[cnt]][cnt]=ch;
				cnt++;
				sig=1;
            } else {
				sig=-1;
            }
        } else {
            if (ch==' '){
				if (sig==-1){
					isnum[cnt]=0;
					data[isnum[cnt]][cnt]='-';
					cnt++;
				}
				sig=1;
            } else {
                while ((ch>='0' && ch<='9') || (ch=='.')) {
                    if (ch=='.') {
                        isdot=1;
                        dotnum=1;
                    } else {
                        if (!isdot) {
                            tmp*=10;
                            tmp+=(ch-'0');
                        } else {
                            dotnum*=10;
                            tmp*=10;
                            tmp+=(ch-'0');
                        }
                    }
                    ch=getchar();
                }
				isnum[cnt]=1;
                if (isdot) {
                    data[isnum[cnt]][cnt]=(tmp/dotnum)*sig;
                } else {
                    data[isnum[cnt]][cnt]=(tmp)*sig;
                }
                isdot=0;
                tmp=0;
                cnt++;
                sig=1;
            }
        }
    } while (ch!='\n');
    return;
}
 
int main() {
	int n;
	scanf("%d\n",&n);
	for (int i=0;i<n;i++){
    getarray();
    cnt--;
    while (cnt>=0) {
		if (!isnum[cnt]){
			char type=data[isnum[cnt]][cnt];
			switch (type){
			case '+':push(pop()+pop());break;
			case '-':push(pop()-pop());break;
			case '*':push(pop()*pop());break;
			case '/':push(pop()/pop());break;
			}
		} else {
		push(data[isnum[cnt]][cnt]);
		}
		cnt--;
    }
    printf("case #%d:\n",i);
    printf("%f\n",pop());
    tmp=0.0;
    p=0,isdot=0,dotnum=0,cnt=0,sig=1;
    }
    return 0;
}
 