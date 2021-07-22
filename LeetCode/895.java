class FreqStack {
    HashMap<Integer, Integer> freq = new HashMap<>();
    HashMap<Integer, Stack<Integer>> repo = new HashMap<>();
    int maxFreq = 0;
    
    public FreqStack() {

    }

    public void push(int val) {
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);
        maxFreq = Math.max(maxFreq, f);
        if (!repo.containsKey(f)) {
            repo.put(f, new Stack<>());
        } 
        repo.get(f).push(val);
    }

    public int pop() {
        int x = repo.get(maxFreq).pop();
        freq.put(x, freq.get(x) - 1);
        if (repo.get(maxFreq).isEmpty()) {
            maxFreq--;
        }
        return x;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
