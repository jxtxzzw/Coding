#include <stdio.h>

int main() {
    int cas;
    scanf ( "%d\n", &cas );

    for ( int t = 0; t < cas; t++ ) {
        int n;
        scanf ( "%d", &n );
        int freq[501] = {0};
        int a;

        for ( int i = 0; i < n; i++ ) {
            scanf ( "%d", &a );
            freq[a]++;
        }

        printf ( "case #%d:\n", t );

        while ( n ) {
            int max = 0;

            for ( int i = 0; i < 501; i++ ) {
                if ( freq[i] > freq[max] ) {
                    max = i;
                }
            }

            for ( int i = 0; i < freq[max]; i++ ) {
                printf ( "%d", max );

                if ( i < freq[max] - 1 ) {
                    printf ( " " );
                }
            }

            n -= freq[max];
            freq[max] = 0;

            if ( n ) {
                printf ( " " );

            } else {
                printf ( "\n" );
            }
        }
    }

    return 0;
}
