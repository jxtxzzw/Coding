#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
 
 
typedef struct Node {
    char value;
    struct Node *next;
} NODE;
 
long long prime[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53};
 
unsigned long long e ( int idx ) {
    unsigned long long ret = 1;
 
    for ( int i = 0; i < idx; i++ ) {
        ret *= prime[i];
    }
 
    return ret;
}
 
NODE *Decimal2LinkedList ( long long N ) {
    NODE * ret = ( NODE * ) malloc ( sizeof ( NODE ) );
 
    static int cnt = 0;
	int idx = cnt;
    if ( N != 1 ) {
        while ( ( N / e ( idx ) ) > prime[idx] - 1 ) {
            idx++;
        }
 
        if ( idx > cnt ) {
            cnt = idx;
        }
 
        ret -> value = N / e ( idx );
 
 
 
        if ( cnt ) {
            cnt--;
 
            ret -> next = Decimal2LinkedList ( N % e ( idx ) );
 
        } else {
            ret -> next = 0;
        }
 
    } else {
        if ( cnt ) {
            cnt--;
            ret -> value = 0;
            ret -> next = Decimal2LinkedList ( N );
 
        } else {
            ret -> value = 1;
            ret -> next = 0;
        }
    }
 
 
 
 
    return ret;
}
 
void solve() {
    long long N;
    scanf ( "%I64d", &N );
    NODE* h = Decimal2LinkedList ( N ), *p;
 
    while ( h ) {
        p = h;
        h = h->next;
        printf ( "%d;", p->value );
        free ( p );
    }
 
    printf ( "\n" );
}
 
int main() {
    int i, t;
    scanf ( "%d\n", &t );
 
    for ( i = 0; i < t; i++ ) {
        printf ( "case #%d:\n", i );
        solve();
    }
 
    return 0;
}