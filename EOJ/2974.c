#include <stdio.h>
#include <string.h>

int main() {
    int cas;
    scanf ( "%d\n", &cas );

    for ( int t = 0; t < cas; t++ ) {
        char st[1000][1000] = {{0}};
        int cnt = 0;

        while ( scanf ( "%s", st[cnt++] ) == 1 ) {
            char ch = getchar();

            if ( ch == '\n' ) { break; }
        }

        int ans = cnt;

        for ( int i = 0; i < cnt; i++ ) {
            if ( stricmp ( st[i], "the" ) == 0
                    || stricmp ( st[i], "a" ) == 0
                    || stricmp ( st[i], "an" ) == 0
                    || stricmp ( st[i], "of" ) == 0
                    || stricmp ( st[i], "for" ) == 0
                    || stricmp ( st[i], "and" ) == 0 ) {
                ans--;
            }
        }

        printf ( "case #%d:\n%d\n", t, ans );
    }

    return 0;
}
