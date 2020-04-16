#include <stdio.h>
#include <stdlib.h>
 
int main() {
    int cas;
    scanf ( "%d", &cas );
 
    for ( int t = 0; t < cas; t++ ) {
        printf ( "case #%d:\n", t );
        int num;
        scanf ( "%d", &num );
        int cnt = 0;
 
        while ( num / 5 ) {
            cnt = cnt + num / 5;
            num = num / 5;
        }
 
        printf ( "%d\n", cnt );
    }
 
 
    return 0;
}