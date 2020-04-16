
/*
#include <stdio.h>
#include <string.h>
#define N 5000
int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int a[N] = {0};
        int b[N] = {0};
        int c[N] = {0};
        int n;
        scanf ( "%d", &n );

        for ( int i = 1; i <= n; i++ ) {
            scanf ( "%d", &a[i] );
            b[n] = 1;
            c[n] = 0;
        }

        int max = 0;
        int p = 0;

        for ( int i = n - 1; i >= 1; i-- ) {
            max = 0;
            p = 0;

            for ( int j = i + 1 ; j <= n; j++ ) {
                if ( ( a[i] < a[j] ) && ( b[j] > max ) ) {
                    max = b[j];
                    p = j;
                }

            }

            if ( p ) {
                b[i] = b[p] + 1;
                c[i] = p;
            }
        }

        max = 0;
        p = 0;

        for ( int i = 1; i <= n; i++ ) {
            if ( b[i] > max ) {
                max = b[i];
                p = i;
            }
        }

        printf ( "maxlong=%d\n", max );
        printf ( "result=" );

        while ( p ) {
            printf ( "%d ", a[p] );
            p = c[p];
        }


    }

    return 0;
}


*/



#include <stdio.h>
#include <string.h>
#define N 5000
int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int a[N] = {0};
        int b[N] = {0};
        int c[N] = {0};
        int n;
        scanf ( "%d", &n );

        for ( int i = 1; i <= n; i++ ) {
            scanf ( "%d", &a[i] );
            b[i] = a[i];
        }

        int max = 0;
        int p = 0;

        for ( int i = n - 1; i >= 1; i-- ) {
            max = 0;
            p = 0;

            for ( int j = i + 1 ; j <= n; j++ ) {
                if ( ( a[i] < a[j] ) && ( b[j] > max ) ) {
                    max = b[j];
                    p = j;
                }
            }

            if ( p ) {
                b[i] = b[p] + a[i];
                c[i] = p;
            }
        }

        max = 0;
        p = 0;

        for ( int i = 1; i <= n; i++ ) {
            if ( b[i] > max ) {
                max = b[i];
                p = i;
            }
        }

        printf ( "%d\n", max );
        /*
        printf ( "result=" );
        while ( p ) {
            printf ( "%d ", a[p] );
            p = c[p];
        }
        */
    }

    return 0;
}
