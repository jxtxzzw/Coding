class Solution {
    // DO NOT use Combinatorial Number, since it will cause integer overflow, unless it is Python or something like that
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        // When rowIndex is 0, return immediately
        for (int row = 1; row <= rowIndex; row++) {
            // The rowIndex-th row will have (rowIndex + 1) columns
            // When col == 0, it is 1.
            // Just calculate the middle columns
            for (int col = 1; col <= row / 2; col++) {
                // row / 2, inclusive, when row is an odd
                // when (i - 1) is updated, (i - 1） + (i) will be wrong, so use the right part
                list.set(col, list.get(row - col) + list.get(col));
            }
            // Symmetrical, copy the rest part
            for (int col = row / 2 + 1; col < row; col++) {
                list.set(col, list.get(row - col));
            }
            // When col == row, it is 1.
            list.add(1);
        }
        // We use only 0, 1, 2, ..., (rowIndex + 1) spaces
        return list;
    }
}