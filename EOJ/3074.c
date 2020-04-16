#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int data[26][6] = {
    {2, 3, 0, 1, 4, 0}, {2, 3, 0, 2, 3, 0}, {2, 3, 0, 3, 2, 0},
    {3, 2, 0, 1, 4, 0}, {3, 2, 0, 2, 3, 0}, {3, 2, 0, 3, 2, 0},
    {4, 1, 0, 1, 4, 0}, {4, 1, 0, 2, 3, 0}, {4, 1, 0, 3, 2, 0},
    {5, 0, 0, 1, 4, 0}, {5, 0, 0, 2, 3, 0}, {5, 0, 0, 3, 2, 0},
    {0, 1, 4, 1, 4, 0}, {0, 1, 4, 2, 3, 0}, {0, 1, 4, 3, 2, 0},
    {0, 2, 3, 1, 4, 0}, {0, 2, 3, 2, 3, 0}, {0, 2, 3, 3, 2, 0}, {0, 2, 3, 4, 1, 0},
    {0, 3, 2, 1, 4, 0}, {0, 3, 2, 2, 3, 0}, {0, 3, 2, 3, 2, 0},
    {0, 4, 1, 1, 4, 0}, {0, 4, 1, 2, 3, 0}, {0, 4, 1, 3, 2, 0}, {0, 4, 1, 4, 1, 0}
};

void solve ( char s[] ) {
    for ( int i = 0; i < strlen ( s ); i++ ) {
        for ( int j = 0; j < data[s[i] - 'A'][0]; j++ ) {
            printf ( "." );
        }

        for ( int j = 0; j < data[s[i] - 'A'][1]; j++ ) {
            printf ( "-" );
        }

        for ( int j = 0; j < data[s[i] - 'A'][2]; j++ ) {
            printf ( "." );
        }

        printf ( "/" );

        for ( int j = 0; j < data[s[i] - 'A'][3]; j++ ) {
            printf ( "." );
        }

        for ( int j = 0; j < data[s[i] - 'A'][4]; j++ ) {
            printf ( "-" );
        }

        for ( int j = 0; j < data[s[i] - 'A'][5]; j++ ) {
            printf ( "." );
        }

        printf ( "%c", i < strlen ( s ) - 1 ? '/' : '\n' );
    }
}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        char s[201];
        scanf ( "%s", s );
        printf ( "case #%d:\n", t );
        solve ( s );
    }

    return 0;
}
