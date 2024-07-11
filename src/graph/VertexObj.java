package graph;

import java.util.ArrayList;

public class VertexObj<V, E> {
    private V info;
    private ArrayList<EdgeObj<V, E>> edges;

    public VertexObj(V info) {
        this.info = info;
        this.edges = new ArrayList<>();
    }

    public V getInfo() {
        return info;
    }

    public ArrayList<EdgeObj<V, E>> getEdges() {
        return edges;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VertexObj<?, ?> vertexObj = (VertexObj<?, ?>) o;
        return info.equals(vertexObj.info);
    }

    public int hashCode() {
        return info.hashCode();
    }

    @Override
    public String toString() {
        return info.toString();
    }
}