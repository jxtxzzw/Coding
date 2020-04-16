#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
char* solve(char * s, const char * s0){
char* pos;
while ((pos = strstr(s,s0))!=0){
	for (int i=0;i<=strlen(s)-strlen(s0);i++){
		*(pos+i)=*(pos+strlen(s0)+i);
	}
}
return s;
}
*/


void solve ( char * s, const char * s0, int len ) {
    char* pos;

    while ( ( pos = strstr ( s, s0 ) ) != 0 ) {
        for ( int i = 0; i < strlen ( s0 ); i++ ) {
            * ( pos + i ) = '0';
        }
    }

    for ( int i = 0; i < len; i++ ) {
        if ( s[i] != '0' ) {
            printf ( "%c", s[i] );
        }
    }

    printf ( "\n" );
}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        char s[2001], s0[101];
        scanf ( "%s%s", s, s0 );
        solve ( s, s0, strlen ( s ) );
    }


    return 0;
}
