#include <stdio.h>
#include <stdlib.h>

char st[11][32];

void readln ( int x ) {
    int i;
    char ch;
    st[x][0] = x;
    i = 1;
    while ( ( ch = getchar() ) != '\n' ) {
        st[x][i] = ch;
        i++;
    }
    st[x][i] = '\n';
    return;
}

void change ( int x, int y ) {
    st[0][0] = st[x][0];
    st[x][0] = st[y][0];
    st[y][0] = st[0][0];
    return;
}

int comp ( int x, int y ) {
    int ret = 0, i = 0;
    char c1, c2;
    while ( 1 ) {
        i++;
        c1 = st[x][i];
        //  if ( c1 >= 'A' && c1 <= 'Z' ) c1 += 32;
        c2 = st[y][i];
       //  if ( c2 >= 'A' && c2 <= 'Z' ) c2 += 32;
        if ( c1 < c2 ) {
            if ( st[x][0] > st[y][0] ) {
                ret = 0;
            } else {
                ret = 1;
            }
            break;
        } else if ( c1 > c2 ) {
            if ( st[x][0] < st[y][0] ) {
                ret = 0;
            }
            else {
                ret = 1;
            }
            break;
        } else {
            continue;
        }
    }
    return ret;
}


void prt ( int x ) {
    int i = 0;
    do {
        i++;
        printf ( "%c", st[x][i] );
    } while ( st[x][i] != '\n' );
    return;
}

int main() {
    int n, i, j;
    scanf ( "%d\n", &n );
    for ( i = 1; i <= n; i++ ) {
        readln ( i );
    }
	int ischanged;
	do {
		ischanged=0;
        for ( i = 1 ; i <= n; i++ ) {
            for ( j = i + 1; j <= n; j++ ) {
                if ( comp ( i, j ) ) {
                    change ( i, j );
                    ischanged=1;
                }
            }
        }
	}while (ischanged);
    for ( i = 1; i <= n; i++ ) {
        for ( j = 1; j <= n; j++ ) {
            if ( st[j][0] == i ) prt ( j );
        }
    }
    return 0;
}
