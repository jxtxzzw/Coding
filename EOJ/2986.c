#include <stdio.h>
#include <math.h>
int main() {
    int cas;
    scanf ( "%d", &cas );
    for ( int t = 0; t < cas; t++ ) {
        int n;
        scanf ( "%d", &n );
        printf ( "case #%d:\n%d\n", t, ( int ) pow ( 2, n ) );
    }
    return 0;
}
