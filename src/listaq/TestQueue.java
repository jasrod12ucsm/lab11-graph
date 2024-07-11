package listaq;

public class TestQueue {
    public static void main(String[] args) {
        Queue<String> q1 = new QueueLink<>();
        try {
            q1.enqueue("lima");

            System.out.println(q1 + "front: " + q1.front() + " back: " + q1.back());
            q1.dequeue();
            System.out.println(q1 + "front: " + q1.front() + " back: " + q1.back());

            // q1.enqueue("Mexico");
            // System.out.println(q1);
            // q1.enqueue("Japon");
            // System.out.println(q1);
            // q1.enqueue("Peru");
            // System.out.println(q1);
            // q1.enqueue("aqp");
            // System.out.println(q1);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}