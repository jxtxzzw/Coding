#include <stdio.h>
#include <stdlib.h>

int comp ( const void *pa , const void *pb ) {
	int ret = 0;
	long long *a = * ( long long ( * ) [2] ) pa;
	long long *b = * ( long long ( * ) [2] ) pb;

	if ( a[1] != b[1] ) {
		if ( a[1] > b[1] ) { ret = -1; }

		else { ret = 1; }

	} else {
		if ( a[0] > b[0] ) { ret = 1; }

		else { ret = -1; }
	}

	return ret;
}

int main() {
	int cas;
	scanf ( "%d", &cas );

	for ( int t = 0; t < cas; t++ ) {
		long long data[10001][2] = {{0}};
		int n;
		scanf ( "%d", &n );

		for ( int i = 0; i < n; i++ ) {
			long long x;
			scanf ( "%lld", &x );
			data[i][0] = x;

			if ( x >= 0 ) {
				long long cnt = 0;

				while ( x ) {
					if ( x % 2 ) { cnt++; }

					x /= 2;
				}

				data[i][1] = cnt;

			} else {
				x = ~x;
				long long cnt = 64;

				while ( x ) {
					if ( x % 2 ) { cnt--; }

					x /= 2;
				}

				data[i][1] = cnt;
			}
		}

		qsort ( data, n, sizeof ( data[0] ), comp );
		printf ( "case #%d:\n", t );

		for ( int i = 0; i < n; i++ ) {
			printf ( "%lld%c", data[i][0], i < n - 1 ? ' ' : '\n' );
		}
	}

	return 0;
}
