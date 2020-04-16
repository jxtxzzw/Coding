#include <stdio.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

#define BUF_SIZE 10000007

void display(int array[], int maxlen)
{
    int i;

    for(i = 0; i < maxlen; i++)
    {
        printf("%d ", array[i]);
    }
    printf("\n");

    return ;
}

void QuickSort(int *arr, int low, int high)
{
    if (low < high)
    {
        int i = low;
        int j = high;

        int temp = arr[low];
        arr[low] = arr[(low+high)/2];
        arr[(low+high)/2] = temp;
        int k = arr[low];
        
        while (i < j)
        {
            while(i < j && arr[j] >= k)
            {
                j--;
            }

            if(i < j)
            {
                arr[i++] = arr[j];
            }

            while(i < j && arr[i] < k)
            {
                i++;
            }

            if(i < j)
            {
                arr[j--] = arr[i];
            }
        }

        arr[i] = k;

        QuickSort(arr, low, i - 1);
        QuickSort(arr, i + 1, high);
    }
}

int main()
{
    int a[BUF_SIZE] = {0};
    int n;
    cin >> n;
    int i;
    for (i = 0; i < n; i++) {
        cin >> a[i];
    }
    sort(a, a+n);

    display(a, n);

    return 0;
}
