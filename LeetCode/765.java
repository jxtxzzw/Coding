class Solution {
    
    private void swap(int[] row, int i, int j) {
        int tmp = row[i];
        row[i] = row[j];
        row[j] = tmp;
    }
    
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] partner = new int[n];
        int[] position = new int[n];

        for (int i = 0; i < n; i++) {
            partner[i] = i % 2 == 0 ? i + 1 : i - 1;
            position[row[i]] = i;
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = partner[position[partner[row[i]]]]; j != i; j = partner[position[partner[row[i]]]]) {
                count++;
                swap(row, i, j);
                swap(position, row[i], row[j]);
            }
        }
        return count;
    }
}

/**
 * Solution: https://leetcode.com/problems/couples-holding-hands/discuss/113362/JavaC%2B%2B-O(N)-solution-using-cyclic-swapping
 */
/*
You may have encountered problems that can be solved using cyclic swapping before (like lc41 and lc268). In this post, I will use a simplified N integers problem to illustrate the cyclic swapping idea and then generalize it to this N couples problem.

I -- N integers problem

Assume we have an integer array row of length N, which contains integers from 0 up to N-1 but in random order. You are free to choose any two numbers and swap them. What is the minimum number of swaps needed so that we have i == row[i] for 0 <= i < N (or equivalently, to sort this integer array)?

First, to apply the cyclic swapping algorithm, we need to divide the N indices into mutually exclusive index groups, where indices in each group form a cycle: i0 --> i1 --> ... --> ik --> i0. Here we employ the notation i --> j to indicate that we expect the element at index i to appear at index j at the end of the swapping process.

Before we dive into the procedure for building the index groups, here is a simple example to illustrate the ideas, assuming we have the following integer array and corresponding indices:

row: 2, 3, 1, 0, 5, 4
idx: 0, 1, 2, 3, 4, 5
Starting from index 0, what is the index that the element at index 0 is expected to appear? The answer is row[0], which is 2. Using the above notation, we have 0 --> 2. Then starting from index 2, what is the index that the element at index 2 is expected to appear? The answer will be row[2], which is 1, so we have 2 --> 1. We can continue in this fashion until the indices form a cycle, which indicates an index group has been found: 0 --> 2 --> 1 --> 3 --> 0. Then we choose another start index that has not been visited and repeat what we just did. This time we found another group: 4 --> 5 --> 4, after which all indices are visited so we conclude there are two index groups.

Now for an arbitrary integer array, we can take similar approaches to build the index groups. Starting from some unvisited index i0, we compute the index i1 at which the element at i0 is expected to appear. In this case, i1 = row[i0]. We then continue from index i1 and compute the index i2 at which the element at i1 is expected to appear. Similarly we have, i2 = row[i1]. We continue in this fashion to construct a list of indices: i0 --> i1 --> i2 --> ... --> ik. Next we will show that eventually the list will repeat itself from index i0, which has two implications:

Eventually the list will repeat itself.
It will repeat itself from index i0, not other indices.
Proof of implication 1: Assume the list will never repeat itself. Then we can follow the above procedure to generate a list containing at least (N + 1) distinct indices. This implies at least one index will be out of the range [0, N-1]. However, we know that each index in the list should represent a valid index into the row array. Therefore, all indices should lie in the range [0, N-1], a contradiction. So we conclude the list will eventually repeat itself.

Proof of implication 2: Assume the list will repeat itself at some index j other than i0. Then we know there exists at least two different indices p and q such that we have p --> j and q --> j, which, according to the definition of the --> notation, implies at the end of the swapping process, we need to place two different elements at index j simultaneously, which is impossible. So we conclude the list must repeat itself at index i0.

Next suppose we have produced two such index groups, g1 and g2, which are not identical (there exists at least one index contained in g1 but not in g2 and at least one index contained in g2 but not in g1). We will show that all indices in g1 cannot appear in g2, and vice versa - - g1 and g2 are mutually exclusive. The proof is straightforward: if there is some index j that is common to both g1 and g2, then both g1 and g2 can be constructed by starting from index j and following the aforementioned procedure. Since each index is only dependent on its predecessor, the groups generated from the same start index will be identical to each other, contradicting the assumption. Therefore g1 and g2 will be mutually exclusive. This also implies the union of all groups will cover all the N indices exactly once.

Lastly, we will show that the minimum number of swaps needed to resolve an index group of size k is given by k - 1. Here we define the size of a group as the number of distinct indices contained in the group, for example:

Size 1 groups: 0 --> 0, 2 --> 2, etc.
Size 2 groups: 0 --> 3 --> 0, 2 --> 1 --> 2, etc.
......
Size k groups: 0 --> 1 --> 2 --> ... --> (k-1) --> 0, etc.
And by saying "resolving a group", we mean placing the elements at each index contained in the group to their expected positions at the end of the swapping process. In this case, we want to put the element at index i, which is row[i], to its expected position, which is row[i] again (the fact that the element itself coincides with its expected position is a result of the placement requirement row[i] == i).

The proof proceeds by using mathematical induction:

Base case: If we have a group of size 1, what is the minimum number of swaps needed to resolve the group? First, what does it imply if a group has size 1? A group of size 1 takes the form j --> j. From the definition of the --> notation, we know the element at index j is expected to appear at index j at the end of the swapping process. Since this is already the case (that is, element at index j is already placed at index j), we don't need to take any further actions, therefore the minimum number of swaps needed is 0, which is the group size minus 1. Hence the base case stands.

Induction case: Assume the minimum number of swaps needed to resolve a group of size m is given by m - 1, where 1 <= m <= k. For a group of size k + 1, we can always use one swap to turn it into two mutually exclusive groups of size k1 and k2, respectively, such that k1 + k2 = k + 1 with 1 <= k1, k2 <= k. This is done by first choosing any two different indices i and j in the group, then swapping the element at index i with that at index j. Before swapping, the group is visualized as: ... --> i --> p --> ... --> j --> q --> .... The swapping operation is equivalent to repointing index i to index q meanwhile repointing index j to index p. This is because the element originally at index i is now at index j, therefore the expected index for the element at index j after swapping will be the same as the one for the element at index i before swapping, which is p, so we have j --> p after swapping. Similarly we have i --> q after swapping. Therefore, the original group becomes two disjoint groups after swapping: ... --> i --> q --> ... and ... --> j --> p --> ..., where each group contains at least one index. Now by the induction assumption, the minimum number of swaps needed to resolve the above two groups are given by k1 - 1 and k2 - 1, respectively. Therefore, the minimum number of swapping needed to resolve the original group of size (k + 1) is given by (k1 - 1) + (k2 - 1) + 1 = k1 + k2 - 1 = k. Hence the induction case also stands.

In conclusion, the minimum number of swaps needed to resolve the whole array can be obtained by summing up the minimum number of swaps needed to resolve each of the index groups. To resolve each index group, we are free to choose any two distinct indices in the group and swap them so as to reduce the group to two smaller disjoint groups. In practice, we can always choose a pivot index and continuously swap it with its expected index until the pivot index is the same as its expected index, meaning the entire group is resolved and all placement requirements within the group are satisfied.

The following is a sample implementation for this problem, where for each pivot index i, the expected index j is computed by taking the value of row[i].

public int miniSwapsArray(int[] row) {
    int res = 0, N = row.length;

    for (int i = 0; i < N; i++) {
		for (int j = row[i]; i != j; j = row[i]) {
			swap(row, i, j);
			res++;
		}
    }

    return res;
}

private void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
}
II -- N couples problem

The N couples problem can be solved using exactly the same idea as the N integers problem, except now we have different placement requirements: instead of i == row[i], we require i == ptn[pos[ptn[row[i]]]], where we have defined two additional arrays ptn and pos:

ptn[i] denotes the partner of label i (i can be either a seat or a person) - - ptn[i] = i + 1 if i is even; ptn[i] = i - 1 if i is odd.

pos[i] denotes the index of the person with label i in the row array - - row[pos[i]] == i.

The meaning of i == ptn[pos[ptn[row[i]]]] is as follows:

The person sitting at seat i has a label row[i], and we want to place him/her next to his/her partner.

So we first find the label of his/her partner, which is given by ptn[row[i]].

We then find the seat of his/her partner, which is given by pos[ptn[row[i]]].

Lastly we find the seat next to his/her partner's seat, which is given by ptn[pos[ptn[row[i]]]].

Therefore, for each pivot index i, its expected index j is given by ptn[pos[ptn[row[i]]]]. As long as i != j, we swap the two elements at index i and j, and continue until the placement requirement is satisfied. A minor complication here is that for each swapping operation, we need to swap both the row and pos arrays.

Here is a list of solutions for Java and C++. Both run at O(N) time with O(N) space. Note that there are several optimizations we can do, just to name a few:

The ptn array can be replaced with a simple function that takes an index i and returns i + 1 or i - 1 depending on whether i is even or odd.

We can check every other seat instead of all seats. This is because we are matching each person to his/her partners, so technically speaking there are always half of the people sitting at the right seats.

There is an alternative way for building the index groups which goes in backward direction, that is instead of building the cycle like i0 --> i1 --> ... --> jk --> i0, we can also build it like i0 <-- i1 <-- ... <-- ik <-- i0, where i <-- j means the element at index j is expected to appear at index i. In this case, the pivot index will be changing along the cycle as the swapping operations are applied. The benefit is that we only need to do swapping on the row array.

Java solution:

public int minSwapsCouples(int[] row) {
    int res = 0, N = row.length;
    
    int[] ptn = new int[N];    
    int[] pos = new int[N];
    
    for (int i = 0; i < N; i++) {
        ptn[i] = (i % 2 == 0 ? i + 1 : i - 1);
        pos[row[i]] = i;
    }
    
    for (int i = 0; i < N; i++) {
        for (int j = ptn[pos[ptn[row[i]]]]; i != j; j = ptn[pos[ptn[row[i]]]]) {
			swap(row, i, j);
			swap(pos, row[i], row[j]);
			res++;
		}
    }
    
    return res;
}

private void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
}
C++ solution:

int minSwapsCouples(vector<int>& row) {
    int res = 0, N = row.size();
        
    vector<int> ptn(N, 0);
    vector<int> pos(N, 0);
        
    for (int i = 0; i < N; i++) {
        ptn[i] = (i % 2 == 0 ? i + 1 : i - 1);
        pos[row[i]] = i;
    }
    
    for (int i = 0; i < N; i++) {
        for (int j = ptn[pos[ptn[row[i]]]]; i != j; j = ptn[pos[ptn[row[i]]]]) {
			swap(row[i], row[j]);
            swap(pos[row[i]], pos[row[j]]);
			res++;
		}
    }
        
    return res;
}
 */
