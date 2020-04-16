#include <stdio.h>
#include <stdlib.h>


int comp ( const void *pa, const void *pb ) {
	int ret = 0;
	int *a = * ( int ( * ) [51] ) pa;
	int *b = * ( int ( * ) [51] ) pb;
	int pos = 0;

	while ( a[pos] == b[pos] ) { pos++; }

	ret = b[pos] - a[pos];
	return ret;
}



int main() {
	int cas;
	scanf ( "%d", &cas );

	for ( int t = 0; t < cas; t++ ) {
		int data[1001][51] = {{0}};
		int row_num;
		scanf ( "%d", &row_num );
		int row = 0;
		int x;
		int col = 0;

		do {
			scanf ( "%d", &x );
			data[row][col++] = x;

			if ( x == -1 ) {row++; col = 0;}
		} while ( row < row_num );

		qsort ( data, row_num, sizeof ( data[0] ), comp );

		for ( int i = 0; i < row_num; i++ ) {
			for ( int j = 0; data[i][j] != -1; j++ ) {
				printf ( "%d%c", data[i][j], data[i][j + 1] == -1 ? '\n' : ' ' );
			}
		}
	}

	return 0;
}