#include <stdio.h>
#include <stdlib.h>

int comp ( const void *a, const void *b ) {
    int ret = 0;
    ret =  * ( int * ) a % 10 - * ( int * ) b % 10;

    if ( ret == 0 ) { ret = * ( int * ) a - * ( int * ) b; }

    return ret;
}


int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        int x;
        int len = 0;
        int n;
        int a[101] = {0};
        scanf ( "%d", &n );

        for ( int i = 0; i < n; i++ ) {
            scanf ( "%d", &x );
            int isExist = 0;

            for ( int j = 0; j < len; j++ ) {
                if ( a[j] == x ) {
                    isExist = 1;
                    break;
                }
            }

            if ( !isExist ) {
                a[len++] = x;
            }
        }

        qsort ( a, len, sizeof ( int ), comp );
        printf ( "case #%d:\n", t );

        for ( int i = 0; i < len; i++ ) {
            printf ( "%d%c", a[i], i < len - 1 ? ' ' : '\n' );
        }
    }

    return 0;
}