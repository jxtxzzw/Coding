class StockSpanner {
    private final Stack<Integer> prices = new Stack<>();
    private final Stack<Integer> counts = new Stack<>();

    public int next(int price) {
        int count = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            count += counts.pop();
            prices.pop();
        }
        prices.push(price);
        counts.push(count);
        return count;
    }
}
