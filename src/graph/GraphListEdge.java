package graph;

import java.util.ArrayList;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> vertices;
    ArrayList<EdgeObj<V, E>> edges;

    public GraphListEdge() {
        this.vertices = new ArrayList<VertexObj<V, E>>();
        this.edges = new ArrayList<EdgeObj<V, E>>();
    }

    public void insertVertex(V data) {
        VertexObj<V, E> vertex = new VertexObj<V, E>(data);
        this.vertices.add(vertex);
    }

    public void insertEdge(V data1, V data2, E info) {
        VertexObj<V, E> vertex1 = searchVertex(data1);
        VertexObj<V, E> vertex2 = searchVertex(data2);

        if (vertex1 != null && vertex2 != null) {
            EdgeObj<V, E> edge = new EdgeObj<V, E>(vertex1, vertex2, info, edges.size());
            this.edges.add(edge);
            vertex1.getEdges().add(edge);
            vertex2.getEdges().add(edge);
        }
    }

    public VertexObj<V, E> searchVertex(V toFind) {
        for (VertexObj<V, E> vertex : vertices) {
            if (vertex.getInfo().equals(toFind)) {
                return vertex;
            }
        }
        return null;
    }

    public boolean searchEdge(V data1, V data2) {
        VertexObj<V, E> vertex1 = searchVertex(data1);
        VertexObj<V, E> vertex2 = searchVertex(data2);

        if (vertex1 != null && vertex2 != null) {
            for (EdgeObj<V, E> edge : edges) {
                if ((edge.getEndVertex1().equals(vertex1) && edge.getEndVertex2().equals(vertex2)) ||
                        (edge.getEndVertex1().equals(vertex2) && edge.getEndVertex2().equals(vertex1))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void bfs(V data) {
        VertexObj<V, E> vertex = findVertex(data);
        if (vertex != null) {
            ArrayList<VertexObj<V, E>> visited = new ArrayList<VertexObj<V, E>>();
            bfsTraversal(vertex, visited);
        }
    }

    private void bfsTraversal(VertexObj<V, E> vertex, ArrayList<VertexObj<V, E>> visited) {
        System.out.println("Visitando vertex: " + vertex.getInfo());
        visited.add(vertex);

        for (EdgeObj<V, E> edge : vertex.getEdges()) {
            VertexObj<V, E> adjacent;
            if (edge.getEndVertex1().equals(vertex)) {
                adjacent = edge.getEndVertex2();
            } else {
                adjacent = edge.getEndVertex1();
            }

            if (!visited.contains(adjacent)) {
                bfsTraversal(adjacent, visited);
            }
        }
    }
}