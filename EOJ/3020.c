#include <stdio.h>
#include <stdlib.h>
 
int main() {
    int cas;
    scanf ( "%d", &cas );
 
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int num;
        scanf ( "%d", &num );
        int len = 0, max = num;
 
        while ( num != 1 ) {
            if ( num % 2 ) {
                num = num * 3 + 1;
                len++;
 
                if ( num > max ) max = num;
 
 
            } else {
                num /= 2;
                len++;
            }
        }
 
        printf ( "%d %d\n", max, len );
    }
 
 
    return 0;
}