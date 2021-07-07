//一个是要读懂题目，到底要什么
//二是注意 map 对 value 排序的技巧
class Solution {
 public int minSetSize(int[] arr) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
            count++;
        }
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });
        int c = 0;
        for (int i = 0; i < list.size(); i++) {
            c += list.get(i).getValue();
            if (c >= count / 2) {
                return i + 1;
            }
        }
        return -1;
    }
}
