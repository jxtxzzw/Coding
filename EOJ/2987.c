#include <stdio.h>

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        int a[4] = {0};
        scanf ( "%d.%d.%d.%d", &a[0], &a[1], &a[2], &a[3] );
        int isCorrect = 1;
        printf ( "case #%d:\n", t );

        for ( int i = 0; i < 4; i++ ) {
            if ( ( a[i] > 255 || a[i] < 0 ) && isCorrect ) {
                printf ( "No %d %d\n", i, a[i] );
                isCorrect = 0;
            }
        }

        if ( isCorrect ) {
            printf ( "Yes\n" );
        }
    }

    return 0;

}