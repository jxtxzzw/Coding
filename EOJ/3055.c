#include <stdio.h>
#include <stdlib.h>
#include <string.h>

double freq[26] = {0};

int comp ( const void * pa, const void * pb ) {
    int ret = 0 ;
    char a = * ( char* ) pa;
    char b = * ( char* ) pb;
    int x = ( a >= 'A' && a <= 'Z' );
    int y = ( b >= 'A' && b <= 'Z' );

    if ( freq[a + ( 'a' - 'A' ) *x - 'a'] > freq[b + ( 'a' - 'A' ) *y - 'a'] ) {
        ret = -1;

    } else if ( freq[a + ( 'a' - 'A' ) *x - 'a'] < freq[b + ( 'a' - 'A' ) *y - 'a'] ) {
        ret = 1;

    } else {
        if ( ( a + ( 'a' - 'A' ) *x ) != ( b + ( 'a' - 'A' ) *y ) ) {
            ret = ( a + ( 'a' - 'A' ) * x ) - ( b + ( 'a' - 'A' ) * y );

        } else {
            ret = b - a;
        }
    }

    return ret;


}

void solve ( char st[] ) {
    qsort ( st, strlen ( st ), sizeof ( char ), comp );
}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int i = 0; i < cas; i++ ) {
        printf ( "case #%d:\n", i );
        char st[101];

        for ( int j = 0; j < 26; j++ ) {
            scanf ( "%lf", &freq[j] );
        }

        getchar();
        scanf ( "%s", st );
        solve ( st );
        printf ( "%s\n", st );
    }


    return 0;
}
