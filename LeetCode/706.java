class MyHashMap {

    private final int RANGE = 1000;

    private final ArrayList<ArrayList<Integer>> map;

    /** Initialize your data structure here. */
    public MyHashMap() {
        map = new ArrayList<>(RANGE);
        for (int i = 0; i < RANGE; i++) {
            map.add(null);
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int l1 = key / RANGE;
        int l2 = key % RANGE;
        if (map.get(l1) == null) {
            ArrayList<Integer> map2 = new ArrayList<>(RANGE);
            for (int i = 0; i < RANGE; i++) {
                map2.add(null);
            }
            map.set(l1, map2);
        }
        map.get(l1).set(l2, value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int l1 = key / RANGE;
        int l2 = key % RANGE;
        if (map.get(l1) == null) {
            return -1;
        }
        return map.get(l1).get(l2) == null ? -1 : map.get(l1).get(l2);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        if (get(key) != -1) {
            int l1 = key / RANGE;
            int l2 = key % RANGE;
            map.get(l1).set(l2, null);
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
