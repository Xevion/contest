import static java.lang.System.*;
import java.util.Queue;
import java.util.PriorityQueue;

class question29 {
    public static void main(String[] args) {
        Queue<Integer> q;
        q = new PriorityQueue<>();
        q.offer(15);
        q.offer(13);
        q.offer(9);
        q.poll();
        q.offer(q.peek());
        q.peek();
        out.println(q);

        out.println(Integer.compare(3, 4));
    }
}