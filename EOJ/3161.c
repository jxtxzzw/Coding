#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main() {
    unsigned int num, p, n, i, tmp;
    scanf ( "%u %u %u", &num, &p, &n );
    tmp = 0;

    for ( i = p - n + 1; i <= p; i++ ) {
        tmp = tmp + pow ( 2, i );
    }

    printf ( "%u", tmp ^ num );
    return 0;
}
