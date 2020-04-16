#include <stdio.h>
#include <stdlib.h>

int comp ( const void *a, const void *b ) {
	return abs ( * ( int * ) a ) - abs ( * ( int * ) b );
}


int main() {
	int n;
	int a[100];

	while ( scanf ( "%d", &n ) == 1 ) {
		for ( int i = 0; i < n; i++ ) {
			scanf ( "%d", &a[i] );
		}

		qsort ( a, n, sizeof ( int ), comp );

		for ( int i = 0; i < n; i++ ) {
			printf ( "%d%c", a[i], i < n - 1 ? ' ' : '\n' );
		}
	}

	return 0;
}
