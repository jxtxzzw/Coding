/*********************************************/
#include <stdio.h>
 
int main() {
    char st[1000] = {0};
    scanf ( "%s", st );
    int i = 0;
 
    while ( st[i] != '.' ) {
        printf ( "%c", st[i] );
        i++;
    }
 
    printf ( "\n" );
    return 0;
}
/*********************************************/
#include <stdio.h>
 
int main() {
    double num;
    scanf ( "%lf", &num );
    printf ( "%d\n", ( int ) num );
    return 0;
}
/*********************************************/
do {
    char ch = getchar();
 
    if ( ch != '.' ) {
        printf ( "%c", ch );
    }
} while ( ch != '.' );
/*********************************************/
int main() {
    char st[1000] = {0};
    scanf ( "%[^.]", st );
    printf ( "%s\n", st );
    return 0;
}
/*********************************************/