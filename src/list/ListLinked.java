package list;

public class ListLinked<T> implements TDAList<T> {

    protected Node<T> first;

    public ListLinked() {
        first = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int search(T ele) {
        Node<T> node = this.first;
        int cont = 0;
        if (node == null) {
            return -1;
        }
        while (node.getNext() != null) {
            cont++;
            if (node.getData().equals(ele)) {
                return cont;
            }
            node = node.getNext();
        }
        return -1;
    }

    public T searchObj(T ele) {
        Node<T> node = this.first;
        int cont = 0;
        if (node == null) {
            return null;
        }
        while (node.getNext() != null) {
            cont++;
            if (node.getData().equals(ele)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public void insertNode(Node<T> node) {
        if (isEmpty()) {
            this.first = node;
        } else {
            Node<T> aux = this.first;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(node);
        }
    }

    public int lenght() {
        Node<T> node = this.first;
        int cont = 0;
        while (node.getNext() != null) {
            cont++;
            node = node.getNext();
        }
        return cont;

    }

    @Override
    public void insertFirst(T ele) {
        Node<T> node = new Node<T>(ele);
        node.setNext(first);
        first = node;
    }

    @Override
    public void insertLast(T ele) {
        Node<T> node = this.first;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        Node<T> eleNode = new Node<T>(ele);
        node.setNext(eleNode);
    }

    @Override
    public void remove(T ele) {
        for (Node<T> prev = null, curr = this.first; curr != null; prev = curr, curr = curr.getNext()) {
            if (curr.getData().equals(ele)) {
                if (prev != null) {
                    prev.setNext(curr.getNext());
                } else {
                    this.first = curr.getNext();
                }
                return;
            }
        }
    }

    @Override
    public void deleteDuplicates() {
        // elimina duplkicados en el linked list
        for (Node<T> node = this.first; node != null; node.getNext()) {
            for (Node<T> node2 = node.getNext(); node2 != null; node2 = node2.getNext()) {
                if (node.getData().equals(node2.getData())) {
                    node.setNext(node2.getNext());
                }
            }
        }
    }

    @Override
    public void insertNth(T data, int position) {
        // inserta el dato en una posicion
        Node<T> node = this.first;
        int cont = 0;
        if (position == 0) {
            insertFirst(data);
            return;
        }
        while (node.getNext() != null) {
            cont++;
            if (cont == position) {
                Node<T> newNode = new Node<T>(data);
                newNode.setNext(node.getNext());
                node.setNext(newNode);
            }
            node = node.getNext();
        }
        if (node.getNext() == null) {
            insertLast(data);
        }
    }

    public T searchElement(T elementToFind) {
        Node<T> currentNode = this.first;

        while (currentNode != null) {
            if (currentNode.getData().equals(elementToFind)) {
                return currentNode.getData();
            }
            currentNode = currentNode.getNext();
        }

        System.out.println("Element not found");
        return null;
    }

    @Override
    public void deleteNth(int position) {
        Node<T> node = this.first;
        int cont = 0;
        if (position == 0) {
            first = first.getNext();
            return;
        }
        while (node.getNext() != null) {
            cont++;
            if (cont == position) {
                node.setNext(node.getNext().getNext());
            }
            node = node.getNext();
        }
    }

    public Node<T> getFirst() {
        return this.first;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> current = this.first; current != null; current = current.getNext()) {
            sb.append(current.getData()).append(" ");
        }
        return sb.toString().trim(); // Elimina el espacio al final
    }

}
