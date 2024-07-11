package graph;

import list.ListLinked;

public class Vertex<E> {

    private E data;
    protected ListLinked<Edge<E>> listAdj;

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new ListLinked<>();
    }

    public E getData() {
        return data;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return data.equals(vertex.data);
    }
    public void setListAdj(ListLinked<Edge<E>> listAdj) {
        this.listAdj = listAdj;
    }
    public ListLinked<Edge<E>> getListAdj() {
        return listAdj;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    public String toString() {
        return data + " -> " + listAdj.toString() + "\n";
    }
}