#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int comp(const void * a, const void * b){
	int ret = 0;
	char* sa= *(char(*)[31])a;
	char* sb= *(char(*)[31])b;
	int da=-1,db=-1;
	if (sa[0]>='0' && sa[0]<='9') {
		sscanf(sa,"%d",&da);
	} else {
		sscanf(sa,"%*[^0-9]%d",&da);
	}
	if (sb[0]>='0' && sb[0]<='9') {
		sscanf(sb,"%d",&db);
	} else {
		sscanf(sb,"%*[^0-9]%d",&db);
	}
	if (da==db){
		ret = strcmp(sa,sb);
	} else{
		ret = da - db;
	}
	return ret;
}

int main()
{
	char st[101][31] ={{0}};
	int cnt=0;
    while (scanf("%s",st[cnt++])!=EOF);
    cnt--;
    qsort(st,cnt,sizeof(st[0]),comp);
    for (int i=0;i<cnt;i++){
		printf("%s%c",st[i],i<cnt-1?' ':'\n');
    }
    return 0;
}
