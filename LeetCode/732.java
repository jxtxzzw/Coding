// 前缀和，但是数据范围太大，不好开数组，用 Map 来存
TreeMap<Integer, Integer> map = new TreeMap<>();

public MyCalendarThree() {

}

public int book(int start, int end) {
    map.put(start,map.getOrDefault(start, 0) + 1);
    map.put(end, map.getOrDefault(end, 0) - 1);

    int ans = 0, max = 0;
    for (int x : map.values()) {
        ans += x;
        max = Math.max(max, ans);
    }
    return max;
}
