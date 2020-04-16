#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
int main() {
    int i, j, n, tot, m;
    int *e = ( int* ) malloc ( 1000000 * sizeof ( int ) );
    i = 2;

    while ( i <= 1000000 )	{
        e[i] = 1;
        i++;
    }


    i = 2;

    while ( i <= 1000000 )	{
        if ( e[i] != 0 ) {
            j = 2;

            while ( i * j <= 1000000 ) {
                e[i * j] = 0;
                j++;
            }
        }

        i++;
    }

    while (scanf ( "%d %d", &n, &m )==2){

      // scanf ( "%d %d", &n, &m );
        i = n;
        tot = 0;

        while ( i <= m ) {
            if ( e[i] != 0 ) {

                tot++;

            }

            i++;
        }

        printf ( "%d\n", tot );
        }
        free(e);
    return 0;
    }
