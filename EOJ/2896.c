#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int order[26] = {0};

int comp ( const void *a, const void *b ) {
	int ret = 0;
	char *sa = ( char * ) a;
	char *sb = ( char * ) b;
	int pos = 0;

	while ( ret == 0 ) {
		char ca = sa[pos];
		char cb = sb[pos];

		if ( ca * cb ) {
			if ( ca > 'Z' ) { ca -= 32; }

			if ( cb > 'Z' ) { cb -= 32; }

			ret = order[ca - 'A'] - order[cb - 'A'];

		} else {
			if ( ca ) {
				ret = 1;

			} else {
				ret = -1;
			}
		}

		pos++;
	}

	return ret;
}



int main() {
	char alphabet[26] = {0};

	while ( scanf ( "%s\n", alphabet ) != EOF ) {
		for ( int i = 0; i < 26; i++ ) {
			order[alphabet[i] - 'A'] = i;
		}

		char *input = ( char * ) malloc ( 1000 );
		char *oinput = input;
		gets ( input );
		int len = 0;
		char st[101][21] = {{0}};

		while ( strlen ( input ) ) {
			sscanf ( input, "%s", st[len++] );

			if ( strstr ( input, " " ) ) {
				input = strstr ( input, " " ) + 1;

			} else { input[0] = '\0'; }
		}

		qsort ( st, len, sizeof ( st[0] ), comp );

		for ( int i = 0; i < len; i++ ) {
			printf ( "%s%c", st[i], i < len - 1 ? ' ' : '\n' );
		}

		free ( oinput );
	}

	return 0;
}
