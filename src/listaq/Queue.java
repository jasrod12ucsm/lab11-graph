package listaq;

import exeptions.ExceptionIsEmpty;

public interface Queue<E> {
    public void enqueue(E x);

    public E dequeue() throws ExceptionIsEmpty;

    public E back() throws ExceptionIsEmpty;
    // include here your code

    public E front() throws ExceptionIsEmpty;

    // include here your code }
    public boolean isEmpty();
}