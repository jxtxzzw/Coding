#include <stdio.h>
#include <string.h>

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        char name[101] = {0};
        scanf ( "%s", name );
        int passwd[6] = {0};

        for ( int i = 0; i < strlen ( name ); i++ ) {
            passwd[i % 6] += name[i];
        }

        for ( int i = 0; i < 6; i++ ) {
            printf ( "%d", passwd[i] % 10 );
        }

        printf ( "\n" );
    }

    return 0;

}
