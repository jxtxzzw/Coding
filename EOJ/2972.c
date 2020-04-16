#include <stdio.h>
#include <string.h>

int main() {
	int cas;
	scanf ( "%d", &cas );

	for ( int t = 0; t < cas; t++ ) {
		int dividend, divisor;
		char ans[1000] = {0};
		scanf ( "%d%d", &dividend, &divisor );
		sprintf ( ( ans + strlen ( ans ) ), "%d", dividend / divisor );
		ans[strlen ( ans )] = '.';
		int isLoop = 0;
		int rest[1000] = {0};
		int pos = 0;
		int beg, end;

		while ( dividend % divisor && !isLoop ) {
			dividend = dividend % divisor * 10;

			for ( int i = 0; i < pos; i++ ) {
				if ( rest[i] == dividend ) {
					beg = i + 1;
					end = pos;
					isLoop = 1;
					break;
				}
			}

			if ( !isLoop ) {
				rest[pos++] = dividend;
				sprintf ( ( ans + strlen ( ans ) ), "%d", dividend / divisor );
			}
		}

		printf ( "case #%d:\n", t );

		if ( isLoop ) {
			printf ( "%s\n%d-%d\n", ans, beg, end );

		} else {
			printf ( "%s\n", ans );
		}
	}

	return 0;
}
