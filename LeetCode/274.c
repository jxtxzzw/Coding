
int cmp(const void *pa, const void *pb)
{
    return *(int *)pa - *(int *)pb;
}

int hIndex(int *citations, int citationsSize)
{
    // Note: When using qsort(), element number should be greater than 1
    if (citationsSize >= 2)
    {
        qsort(citations, citationsSize, sizeof(int), cmp);
    }
    int ans = 0;
    int index = 0;
    for (int h = 1; h <= citationsSize; h++)
    {
        // Note: Array index bounded
        while (index<citationsSize && h> citations[index])
        {
            index++;
        }
        if (citationsSize - index >= h)
        {
            ans = h;
        }
    }
    return ans;
}

// It is worth considering that if we need binary search. n possible values of h, each one will be called binary search, so it is O(n log n). But the index will only ever be +1, not going back, so it will only be traversed once at most, which is O(n).
