
class MedianFinder {

    private static PriorityQueue<Integer> min;
    private static PriorityQueue<Integer> max;

    public MedianFinder() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
    }

    public void addNum(int num) {
        if (max.isEmpty() || num < max.peek()) {
            max.add(num);
        } else {
            min.add(num);
        }
        while (true) {
            int minSize = min.size();
            int maxSize = max.size();
            if (minSize == maxSize || minSize + 1 == maxSize) {
                break;
            } else {
                if (minSize < maxSize) {
                    min.add(max.poll());
                } else {
                    max.add(min.poll());
                }
            }
        }
    }

    public double findMedian() {
        assert !max.isEmpty();
        if (max.size() == min.size()) {
            return 0.5 * (max.peek() + min.peek());
        } else {
            return max.peek();
        }
    }
}