#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define LEN 100

int comp ( const void *a, const void *b ) {
	int ret = 0;
	char *s1 = * ( char ( * ) [LEN] ) a;
	char *s2 = * ( char ( * ) [LEN] ) b;
	ret = strcmp ( s1, s2 );
	return ret;
}

int main() {
	int cas;
	scanf ( "%d\n", &cas );

	for ( int t = 0; t < cas; t++ ) {
		char st[501] = {0};
		gets ( st );
		st[strlen ( st )] = '.';
		int num = 0;
		int pos = 0;
		char wordList[LEN][LEN] = {{0}};

		for ( int i = 0; i < strlen ( st ); i++ ) {
			if ( st[i] >= 'a' && st[i] <= 'z' ) {
				wordList[num][pos++] = st[i];

			} else {
				if ( pos != 0 ) {
					num++;
				}

				pos = 0;

				for ( int j = 0; j < num - 1; j++ ) {
					if ( !strcmp ( wordList[j], wordList[num - 1] ) ) {
						num--;

						for ( int k = 0; k < LEN; k++ ) {
							wordList[num][k] = 0;
						}

						break;
					}
				}
			}
		}

		qsort ( wordList, num, sizeof ( wordList[0] ), comp );
		printf ( "case #%d:\n", t );

		for ( int i = 0; i < num; i++ ) {
			printf ( "%s%c", wordList[i], i < num - 1 ? ' ' : '\n' );
		}
	}

	return 0;
}
