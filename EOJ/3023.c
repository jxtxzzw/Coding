#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
 
void getSplit ( char * st, char* stList ) {
    for ( int i = 0; i < strlen ( st ); i++ ) {
        int isExist = 0;
 
        for ( int j = 0; j < strlen ( stList ); j++ ) {
            if ( st[i] == stList[j] ) {
                isExist = 1;
            }
        }
 
        if ( !isExist ) {
            stList[strlen ( stList )] = st[i];
        }
    }
 
 
}
 
int comp0(const void * a, const void * b){
int ret = 0;
ret = *(char*)a -*(char*)b;
 
return ret;
}
 
int comp(const void * a, const void * b){
int ret = 0;
ret = strcmp(*(char (*)[17])a,*(char (*)[17])b);
 
return ret;
}
 
int main() {
    int cas;
    scanf ( "%d", &cas );
 
    for ( int t = 0; t < cas; t++ ) {
			printf("case #%d:\n",t);
        char st[17] = {0};
        char stList[17] = {0};
        char data[65536][17] = {{0}};
        scanf ( "%s", st );
        getSplit ( st, stList );
        qsort(stList,strlen(stList),sizeof(char),comp0);
        int cnt = pow ( 2, strlen(stList)) -1;
 
        for ( int i = 0; i < cnt; i++ ) {
            int cur = 0;
            int num = i+1;
            while ( num ) {
                if ( num % 2 ) {
					int j = strlen(data[i]);
                    data[i][j] = stList[cur];
                }
 
                cur++;
                num /= 2;
            }
        }
		qsort(data,cnt,sizeof(char)*17,comp);
 
         for ( int i = 0; i < cnt; i++ ) {
             printf ( "%s\n", data[i] );
         }
 
    }
 
 
 
 
 
 
    return 0;
}