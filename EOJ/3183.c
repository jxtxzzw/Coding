/***************************************************************/
/*                                                             */
/*  DON'T MODIFY main function ANYWAY!                         */
/*                                                             */
/***************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define N 100
 
typedef struct {
    int x,y;
} Point;
 
double dist (Point a) {
    return sqrt(a.x*a.x+a.y*a.y);
}
 
int ord(Point a) {
    if (a.x>=0 && a.y >=0) return 1;
    if (a.x<0 && a.y >=0) return 2;
    if (a.x<=0 && a.y <0) return 3;
    if (a.x>0 && a.y <0) return 4;
}
 
 
int comp(const void * pa, const void * pb) {
    int ret=0;
    Point a=*(Point *)pa;
    Point b=*(Point *)pb;
    if( dist(a)>dist(b)) {
        ret=1;
    } else if (dist(a)<dist(b)) {
        ret=-1;
    } else {
 
        if (ord(a)<ord(b)) {
            ret=-1;
 
        } else if (ord(a)>ord(b)) {
            ret=1;
        } else {
            ret=abs(a.x)-abs(b.x);
        }
    }
    return ret;
}
 
 
/********** Specification of SortPoints **********/
void SortPoints(Point *p, int n)
/* PreCondition:
      p points to an array with n coordinate points
   PostCondition:
      array is sorted satisfying to the specification
*/
 
{
    //TODO: your function definition
    qsort(p,n,sizeof(p[0]),comp);
 
 
 
}
 
 
/***************************************************************/
 
int main() {
    Point a[N];
    int n,i,t,cas;
    scanf("%d",&cas);
    for(t=0; t<cas; t++) {
        scanf("%d",&n);
        for (i=0; i<n; i++) scanf("%d%d",&a[i].x,&a[i].y);
        /***** function SortPoints is called here *****/
        SortPoints(a,n);
        /****************************************/
        printf("case #%d:\n",t);
        for (i=0; i<n; i++) printf("(%d,%d)%c",a[i].x,a[i].y,i<n-1?' ':'\n');
    }
    return 0;
}