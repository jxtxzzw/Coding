#include <stdio.h>
#include <stdlib.h>
#include <math.h>

double f(double x) {
    return (2*pow(x,3)-4*pow(x,2)+3*x-6);
}

double f1(double x) {
    return (6*pow(x,2)-8*x+3);
}

int main() {
int cnt=0;
    double x=1.5;
    while (fabs(f(x))>10E-15) {
        x=(-(f(x)))/(f1(x))+x;
        cnt++;
    }
    printf("%0.2f %d\n",x,cnt);
    return 0;
}
