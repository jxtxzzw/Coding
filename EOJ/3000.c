#include <stdio.h>
#include <string.h>
 
int main() {
    int cas;
    scanf ( "%d\n", &cas );
 
    for ( int t = 0; t < cas; t++ ) {
 
        char st[1000] = {0};
        gets ( st );
 
        for ( int i = 0; i < strlen ( st ); i++ ) {
            if ( st[i] >= 'A' && st[i] <= 'Z' ) {
                st[i] = ( st[i] - 'A' + 13 ) % 26 + 'A';
 
            } else if ( st[i] >= 'a' && st[i] <= 'z' ) {
                st[i] = ( st[i] - 'a' + 13 ) % 26 + 'a';
            }
        }
 
        printf ( "case #%d:\n%s\n", t, st );
 
    }
 
 
    return 0;
}