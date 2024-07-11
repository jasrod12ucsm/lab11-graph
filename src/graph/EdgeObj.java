package graph;

public class EdgeObj<V, E> {
    private VertexObj<V, E> endVertex1;
    private VertexObj<V, E> endVertex2;
    private E info;
    private int id; // Identificador opcional si lo necesitas

    public EdgeObj(VertexObj<V, E> endVertex1, VertexObj<V, E> endVertex2, E info, int id) {
        this.endVertex1 = endVertex1;
        this.endVertex2 = endVertex2;
        this.info = info;
        this.id = id;
    }

    public VertexObj<V, E> getEndVertex1() {
        return endVertex1;
    }

    public VertexObj<V, E> getEndVertex2() {
        return endVertex2;
    }

    public E getInfo() {
        return info;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "first vertex= (" + endVertex1.getInfo() + " -- " + info + ") end vertex=(" + endVertex2.getInfo() + ")";
    }
}