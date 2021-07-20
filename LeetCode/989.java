class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        for (int x : A )
            list.add(x);
        Collections.reverse(list);        
        list.set(0, list.get(0) + K);
        int index = 0;
        while (index < list.size() && list.get(index) >= 10) {
            int x = list.get(index);
            list.set(index, x % 10);
            if (index == list.size() - 1)
                list.add(x / 10);
            else
                list.set(index + 1, list.get(index + 1) + x / 10);
            index++;
        }
        Collections.reverse(list);
        return list;
    }
}
