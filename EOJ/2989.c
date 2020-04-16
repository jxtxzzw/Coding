#include <stdio.h>
#include <string.h>

int main() {
    int cas;
    scanf ( "%d", &cas );
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        char st[101] = {0};
        int a = 0, b = 0, n = 0;
        scanf ( "%s", st );

        for ( int i = 0; i < strlen ( st ); i++ ) {
            if ( st[i] == 'A' ) a++;
            if ( st[i] == 'B' ) b++;
            if ( st[i] == 'N' ) n++;
        }

        while ( a + b + n > 0 ) {
            if ( n > 0 ) {
                printf ( "N" );
                n--;
            }
            if ( b > 0 ) {
                printf ( "B" );
                b--;
            }
            if ( a > 0 ) {
                printf ( "A" );
                a--;
            }
        }
        printf ( "\n" );
    }
    return 0;
}
