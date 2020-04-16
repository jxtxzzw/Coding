#include <stdio.h>
#include <stdlib.h>

int main() {
    char ch;
    int count = 0;
    int num = 0;
    int max = 0;
    int tmp = 0;

    while ( scanf ( "%c", &ch ) == 1 ) {
        if ( ch == '\n' ) {
            if ( tmp > max ) max = tmp;

            tmp = 0;
            num++;

        } else {
            count++;
            tmp++;
        }
    }

    printf ( "%d,%d,%d", count, num, max );
    return 0;
}
