#include <stdio.h>
#include <stdlib.h>
#define N 9
int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );

        int a[N] = {0};

        int max = 8;

        for ( int i = 8; i >= 0; i-- ) {
            scanf ( "%d", &a[i] );
        }

        while ( a[max] == 0 && max > 0 ) {
            max--;
        }

        for ( int i = max; i >= 0; i-- ) {
            if ( a[i] != 0 ) {
                if ( a[i] < 0 ) {
                    printf ( "-" );
                    a[i] = -a[i];

                } else {
                    if ( i != max ) {
                        printf ( "+" );
                    }
                }

                if ( a[i] != 1 ) {
                    printf ( "%d", a[i] );

                } else if ( i == 0 ) {
                    printf ( "%d", a[i] );
                }

                if ( i != 0 ) {
                    printf ( "x" );

                    if ( i != 1 ) {
                        printf ( "^%d", i );
                    }
                }

            } else if ( i == 0 && max == 0 ) {
                if ( max == 0 ) {
                    printf ( "0" );
                }
            }
        }

        printf ( "\n" );
    }



    return 0;
}
