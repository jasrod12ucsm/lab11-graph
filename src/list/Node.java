package list;
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public Node(T d, Node<T> n) {
        data = d;
        next = n;
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> n) {
        this.next = n;
    }

    public void setData(T d) {
        this.data = d;
    }

    @Override
    public String toString() {
        // retorna el tostring de la lista completa de nodos que habar partiendo de este
        return this.data + " " + this.next;
    }

}
