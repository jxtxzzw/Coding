#include <stdio.h>
#include <stdlib.h>

typedef struct {
	long long num;
	int score;
} student;

int comp ( const void *pa, const void *pb ) {
	int ret = 0;
	student a = * ( student * ) pa;
	student b = * ( student * ) pb;

	if ( a.score > b.score ) {
		ret = -1;

	} else if ( a.score < b.score ) {
		ret = 1;

	} else {
		if ( a.num > b.num ) {
			ret = 1;

		} else if ( a.num < b.num ) {
			ret = -1;
		};
	}

	return ret;
}
void sort ( student students[], int len ) {
	qsort ( students, len, sizeof ( student ), comp );
}
int main() {
	int n;
	scanf ( "%d", &n );
	long long x;
	int y;
	int len = 0;
	student students[105];

	while ( n-- ) {
		scanf ( "%lld%d", &x, &y );

		if ( y >= 60 ) {
			students[len].num = x;
			students[len].score = y;
			len++;
		}
	}

	sort ( students, len );

	for ( int i = 0; i < len; i++ ) {
		printf ( "%lld %d\n", students[i].num, students[i].score );
	}

	return 0;
}
