#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXN 57

void input( char * s,int *num ) {
    int pos = 0;
    int coeff = 0;
    int expo=0;
	do {
    if ( s[pos]=='x' ) {
        coeff=1;
        pos++;
    } else if ( s[pos]=='+' && s[pos+1]=='x' )  {
        coeff=1;
        pos+=2;
    } else if ( s[pos]=='-' && s[pos+1]=='x' ) {
        coeff=-1;
        pos+=2;
    } else {
        if ( s[pos]=='+' ) pos++;
        sscanf( s+pos,"%d",&coeff );
        while ( pos<strlen( s ) && s[pos++]!='x' );
    }
    if ( pos==strlen( s ) ) 
        expo = 0;
     else if ( s[pos]!='^' ) 
        expo=1;
     else {
        pos++;
        sscanf( s+pos,"%d",&expo );
        while ( s[pos]>='0' && s[pos]<='9' ) pos++;
    }
    num[expo]=coeff;
}
	while (pos!=strlen(s));
}

int main() {
    char s[107]= {0};
    while (scanf( "%s",s )!=EOF){
		int a[MAXN]= {0};
		int b[MAXN]= {0};
		int ans[2*MAXN]= {0};
		input( s,a );
		scanf( "%s",s );
		input( s,b );
		for ( int i=0; i<MAXN; i++ ) 
			for ( int j=0; j<MAXN; j++ ) 
				ans[i+j]+=a[i]*b[j];
		int high = 2*MAXN-1;
		int low = 0;
		while (ans[low]==0) low++;
		while ( high>=low ) {
			if ( ans[high] ) printf( "%d%c",ans[high],high==low?'\n':' ');
			high--;
		}
    }
    return 0;
}
