class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        LinkedList<Integer> keys = new LinkedList<>();
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        for (Integer key : rooms.get(0)) {
            if (!keys.contains(key) && !visited[key]) {
                keys.push(key);
            }
        }
        while (!keys.isEmpty()) {
            int i = keys.poll();
            visited[i] = true;
            for (Integer key : rooms.get(i)) {
               if (!keys.contains(key) && !visited[key]) {
                    keys.push(key);
                }
            }
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
