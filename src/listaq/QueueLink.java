package listaq;
import list.*;
import exeptions.ExceptionIsEmpty;

public class QueueLink<E> implements Queue<E> {
    private Node<E> first;
    private Node<E> last;

    public QueueLink() {
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x) {
        Node<E> aux = new Node<E>(x);
        if (this.isEmpty()) {
            this.first = aux;
        } else
            this.last.setNext(aux);
        this.last = aux;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        Node<E> prim = this.first;
        E info = prim.getData();
        Node<E> aux = this.first.getNext();
        this.first = aux;
        return info;
    }

    public E back() throws ExceptionIsEmpty {
        // include here your code
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        return this.last.getData();
    }

    public E front() throws ExceptionIsEmpty {
        return this.first.getData();
    }

    // include here your code }
    public boolean isEmpty() {
        return this.first == null;
    }

    // The elements must be included in the chain from the one at the front
    // to the one at the back of the queue.
    public String toString() {

        String str = new String();
        Node<E> aux = this.first;
        while (aux != null) {
            str += aux.getData() + " ";
            aux = aux.getNext();
        }
        return str;
    }
}
