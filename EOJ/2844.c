#include <stdio.h>
#include <stdlib.h>

int ascending ( const void *a, const void *b ) {
	return * ( int * ) a - * ( int * ) b;
}

int descending ( const void *a, const void *b ) {
	return * ( int * ) b - * ( int * ) a;
}

int main() {
	char ch;
	int x;
	int len = 0;
	int a[101] = {0};
	scanf ( "%c", &ch );

	while ( scanf ( "%d", &x ) == 1 ) {
		int isExist = 0;

		for ( int i = 0; i < len; i++ ) {
			if ( a[i] == x ) {
				isExist = 1;
				break;
			}
		}

		if ( !isExist ) {
			a[len++] = x;
		}
	}

	if ( ch == 'A' ) {
		qsort ( a, len, sizeof ( int ), ascending );

	} else {
		qsort ( a, len, sizeof ( int ), descending );
	}

	for ( int i = 0; i < len; i++ ) {
		printf ( "%d%c", a[i], i < len - 1 ? ' ' : '\n' );
	}

	return 0;
}