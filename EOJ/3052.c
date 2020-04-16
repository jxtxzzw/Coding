#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
int isNotTarget ( char st[] ) {
    int ret = 0;
 
    for ( int i = strlen ( st ) - 2; i >= 0; i-- ) {
        if ( st[i] == st[i + 1] ) {
            ret = i + 1;
        }
    }
 
    return ret;
}
/***************************
void add ( char st[], int cur ) {
    while ( st[cur] == '9' && cur > 0 ) {
        st[cur] = '0';
        cur--;
    }
 
    if ( st[cur] != '9' ) {
        st[cur]++;
 
    } else {
        st[cur] = '0';
        st[strlen ( st ) + 1] = '\0';
 
        for ( int i = strlen ( st ); i > 0; i-- ) {
            st[i] = st[i - 1];
        }
 
        st[0] = '1';
    }
}
***************************/
 
void add ( char st[], int cur ) {
	for (int i =cur+1;i<strlen(st);i++){
		st[i]='0';
	}
 
    while ( st[cur] == '9' && cur > 0 ) {
        st[cur] = '0';
        cur--;
    }
 
    if ( st[cur] != '9' ) {
        st[cur]++;
 
    } else {
        st[cur] = '0';
        st[strlen ( st ) + 1] = '\0';
 
        for ( int i = strlen ( st ); i > 0; i-- ) {
            st[i] = st[i - 1];
        }
 
        st[0] = '1';
    }
}
 
void solve ( char st[] ) {
 
    int cur = strlen ( st ) - 1;
 
    do {
        add ( st, cur );
        cur = isNotTarget ( st );
    } while ( cur );
 
    printf ( "%s\n", st );
}
 
 
 
int main() {
    int cas;
    scanf ( "%d", &cas );
 
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        char st[1000];
        scanf ( "%s", st );
        solve ( st );
    }
 
 
 
    return 0;
}