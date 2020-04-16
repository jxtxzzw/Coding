#include <stdio.h>
#include <stdlib.h>

int amount;

void pay ( int x ) {
    int cnt = 0;

    while ( amount - x >= 0 ) {
        cnt++;
        amount -= x;
    }

    printf ( "%d%c", cnt, x == 1 ? '\n' : ' ' );
    return ;
}

int main() {
    int cas;
    scanf ( "%d", &cas );

    for ( int t = 0; t < cas; t++ ) {
        scanf ( "%d", &amount );
        printf ( "case #%d:\n", t );
        pay ( 100 );
        pay ( 50 );
        pay ( 20 );
        pay ( 10 );
        pay ( 5 );
        pay ( 1 );
    }

    return 0;
}
