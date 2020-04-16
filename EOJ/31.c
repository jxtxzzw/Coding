#include <stdio.h>

#define MAX 1000

typedef struct {
	int x, y, z;
} Point;

//********** Specification of NearPoints **********
int NearPoints (Point *p, int n) {
	const int DISTANCE = 100;
	int ret = 0;

	for (int i = 0; i < n; ++i) {
		Point pt = * (p + i);

		if (pt.x >= DISTANCE || pt.x <= -DISTANCE) {
			continue;
		}

		if (pt.y >= DISTANCE || pt.y <= -DISTANCE ) {
			continue;
		}

		if (pt.z >= DISTANCE || pt.z <= -DISTANCE ) {
			continue;
		}

		if (pt.x * pt.x + pt.y * pt.y + pt.z * pt.z < DISTANCE * DISTANCE) {
			// if (!isExist(p,pt,i))
			ret++;
		}
	}

	return ret;
}

/***************************************************************/
int main() {
	int n, i;
	Point p[MAX];
	scanf ("%d", &n);

	for (i = 0; i < n; i++) {
		scanf ("%d%d%d", &p[i].x, &p[i].y, &p[i].z);
	}

	printf ("%d\n", NearPoints (p, n) );
	return 0;
}
