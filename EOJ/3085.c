#include <stdio.h>
#include <stdlib.h>

int main() {
    int num, a[1000], i = 0, tot = 0;
    scanf ( "%d", &num );

    while ( num > 0 ) {
        a[i] = num % 2;
        i++;
        num = num / 2;
    }

    num = i;

    for ( i = 0; i < num; i++ ) {
        if ( a[i] == 1 ) tot++;
    }

    printf ( "%d", tot );
    return 0;
}
