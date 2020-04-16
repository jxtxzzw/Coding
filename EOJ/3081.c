#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main() {
    int d, p, r;
    scanf ( "%d %d %d", &d, &p, &r );
    printf ( "%d\n", ( int ) ( ( log10 ( p / ( p - d * r / 100.0 ) ) / log10 ( 1 + r / 100.0 ) ) + 0.5 ) );
    return 0;
}
