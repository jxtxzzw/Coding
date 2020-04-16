import java.util.*;

public class Hello {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int action;
        TreeSet<Customer> q = new TreeSet<>();
        LOOP:
        while (true) {
            action = in.nextInt();
            switch (action) {
                case 0:
                    break LOOP;
                case 1:
                    q.add(new Customer(in.nextInt(), in.nextInt()));
                    break;
                case 2:
                    System.out.println(q.isEmpty() ? "0" : q.pollFirst().getId());
                    break;
                case 3:
                    System.out.println(q.isEmpty() ? "0" : q.pollLast().getId());
                    break;
                default:
                    break;
            }
        }
     }
}

class Customer implements Comparable<Customer> {
    private int id;
    private int priority;

    public Customer(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(Customer c) {
        return c.priority - priority;
    }
}