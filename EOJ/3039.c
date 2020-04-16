#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int comp ( const void *pa, const void *pb ) {
	int ret = 0;
	long long *a = * ( long long ( * ) [2] ) pa;
	long long *b = * ( long long ( * ) [2] ) pb;

	if ( a[0] > b[0] ) {
		ret = -1;

	} else if ( a[0] < b[0] ) {
		ret = 1;
	}

	if ( ret == 0 ) {
		if ( a[1] > b[1] ) {
			ret = 1;

		} else {
			ret = -1;
		}
	}

	return ret;
}
void sort ( long long ( *a ) [2], int n ) {
	qsort ( a, n, sizeof ( a[0] ), comp );
}

int main() {
	int cas;
	scanf ( "%d", &cas );

	for ( int t = 0; t < cas; t++ ) {
		int n;
		scanf ( "%d", &n );
		long long a[10001][2];

		for ( int i = 0; i < n; i++ ) {
			char st[100] = {0};
			scanf ( "%s", st );

			if ( st[0] == '-' ) {
				a[i][0] = st[1] + '0';

			} else {
				a[i][0] = st[0] + '0';
			}

			sscanf ( st, "%lld", &a[i][1] );
		}

		sort ( a, n );
		printf ( "case #%d:\n", t );

		for ( int i = 0; i < n; i++ ) {
			printf ( "%lld%c", a[i][1] , i < n - 1 ? ' ' : '\n' );
		}
	}

	return 0;
}
