
#include <stdio.h>
#define N 10
void input ( int* p, int n ) {
    for ( int i = 0; i < n; i++ ) {
        scanf ( "%d", p++ );
    }

    return;
}

void process ( int* p, int n ) {
    int *min = p, *max = p;

    for ( int i = 0; i < n; i++ ) {
        if ( * ( p + i ) < *min ) {
            min = p + i;

        } else if ( * ( p + i ) > *max ) {
            max = p + i;
        }
    }

    int t;

    if ( max == p ) {
        t = *p;
        *p = *min;
        * ( p + n - 1 ) = t;

    } else {
        t = *min;
        *min = *p;
        *p = t;
        t = *max;
        *max = * ( p + n - 1 );
        * ( p + n - 1 ) = t;
    }

    return;
}


void output ( int* p, int n ) {
    for ( int i = 0; i < n - 1; i++ ) {
        printf ( "%d ", *p++ );
    }

    printf ( "%d\n", *p++ );
    return;
}


int main() {
    int a[N], n;
    scanf ( "%d", &n );
    input ( a, n );
    process ( a, n );
    output ( a, n );
    return 0;
}

