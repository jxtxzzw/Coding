#include <stdio.h>
#include <string.h>

int main() {
    int cas;
    scanf ( "%d", &cas );
    for ( int t = 0; t < cas; t++ ) {
        char s1[100] = {0}, s2[100] = {0}, s[100] = {0};
        char rs1[100] = {0}, rs2[100] = {0}, rs[100] = {0};
        scanf ( "%s", s1 );
        scanf ( "%s", s2 );
        scanf ( "%s", s );
        strcpy ( rs1, s1 );
        strrev ( rs1 );
        strcpy ( rs2, s2 );
        strrev ( rs2 );
        strcpy ( rs, s );
        strrev ( rs );
        int max = 0;
        if ( strstr ( s, s1 ) && strstr ( rs, rs2 ) ) {
            int beg = strstr ( s, s1 ) - s;
            int end = strstr ( rs, rs2 ) - rs;
            beg = beg + strlen ( s1 ) - 1 ;
            end = strlen ( s ) - end - strlen ( s2 );
            if ( end - beg - 1 > max ) max = end - beg - 1;
        }
        if ( strstr ( s, s2 ) && strstr ( rs, rs1 ) ) {
            int beg = strstr ( s, s2 ) - s;
            int end = strstr ( rs, rs1 ) - rs;
            beg = beg + strlen ( s2 ) - 1 ;
            end = strlen ( s ) - end - strlen ( s1 );
            if ( end - beg - 1 > max ) max = end - beg - 1;
        }
        printf ( "case #%d:\n%d\n", t, max );
    }
    return 0;
}
