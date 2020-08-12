
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