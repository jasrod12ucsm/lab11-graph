package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import exeptions.ExceptionIsEmpty;
import list.ListLinked;
import list.Node;
import listaq.Queue;
import listaq.QueueLink;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    public void insertVertex(E element) {
        Vertex<E> newVertex = new Vertex<E>(element);

        if (this.listVertex.search(newVertex) == -1) {
            System.out.println("El vertice ya exite en el grafo");
            return;
        }

        this.listVertex.insertLast(newVertex);
    }

    public ArrayList<E> bfsPath(E startVertex, E endVertex) throws ExceptionIsEmpty {
        Vertex<E> start = this.listVertex.searchObj(new Vertex<E>(startVertex));
        Vertex<E> end = this.listVertex.searchObj(new Vertex<E>(endVertex));

        if (start == null || end == null) {
            System.out.println("Uno de los vértices no existe.");
            return null;
        }

        Map<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
        Queue<Vertex<E>> queue = new QueueLink<>();
        ListLinked<Vertex<E>> visited = new ListLinked<>();

        queue.enqueue(start);
        visited.insertLast(start);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            Vertex<E> current = queue.dequeue();

            Node<Edge<E>> adjNode = current.listAdj.getFirst();
            while (adjNode != null) {
                Edge<E> edge = adjNode.getData();
                Vertex<E> neighbor = edge.getRefDest();

                if (visited.search(neighbor) == -1) {
                    predecessors.put(neighbor, current);
                    visited.insertLast(neighbor);
                    queue.enqueue(neighbor);

                    if (neighbor.equals(end)) {
                        found = true;
                        break;
                    }
                }

                adjNode = adjNode.getNext();
            }
        }

        // Reconstruir el camino si se encontró el vértice final
        if (found) {
            ArrayList<E> path = new ArrayList<>();
            Vertex<E> step = end;
            while (step != null) {
                path.add(step.getData());
                step = predecessors.get(step);
            }

            // Invertir el camino manualmente
            ArrayList<E> reversedPath = new ArrayList<>();
            for (int i = path.size() - 1; i >= 0; i--) {
                reversedPath.add(path.get(i));
            }

            return reversedPath;
        } else {
            System.out.println("No se encontró un camino entre los vértices.");
            return null;
        }
    }

    public void insertEdge(E origenVertice, E destinoVertice) {
        Vertex<E> origen = this.listVertex.searchObj(new Vertex<E>(origenVertice));
        Vertex<E> destino = this.listVertex.searchObj(new Vertex<E>(destinoVertice));

        if (origen == null || destino == null) {
            System.out.println("Error: Los vértices de origen y/o destino no existen en el grafo.");
        } else {
            if (origen.listAdj.search(new Edge<>(destino)) != -1) {
                System.out.println("Error: La arista ya fue insertada previamente en el grafo.");
            } else {
                origen.listAdj.insertFirst(new Edge<E>(destino));
                destino.listAdj.insertFirst(new Edge<E>(origen));
                System.out.println(
                        "Se ha añadido correctamente la arista entre " + origenVertice + " y " + destinoVertice + ".");
            }
        }
    }

    public void insertEdgeWeight(E v, E z, int weight) {
        Vertex<E> vertexV = this.listVertex.searchObj(new Vertex<E>(v));
        Vertex<E> vertexZ = this.listVertex.searchObj(new Vertex<E>(z));

        if (vertexV == null || vertexZ == null) {
            System.out.println("Uno de los vértices no existe.");
            return;
        }

        Edge<E> newEdge = new Edge<>(vertexZ, weight);
        vertexV.listAdj.insertLast(newEdge);

        // Para grafos no dirigidos, agregamos la arista en la dirección opuesta también
        Edge<E> reverseEdge = new Edge<>(vertexV, weight);
        vertexZ.listAdj.insertLast(reverseEdge);
    }

    public ArrayList<E> shortPath(E startVertex, E endVertex) {
        // Inicialización
        Vertex<E> start = this.listVertex.searchObj(new Vertex<E>(startVertex));
        Vertex<E> end = this.listVertex.searchObj(new Vertex<E>(endVertex));

        if (start == null || end == null) {
            System.out.println("Uno de los vértices no existe.");
            return null;
        }

        Map<Vertex<E>, Integer> distances = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
        ArrayList<Vertex<E>> vertices = new ArrayList<>();

        Node<Vertex<E>> auxVertex = listVertex.getFirst();
        while (auxVertex != null) {
            Vertex<E> vertex = auxVertex.getData();
            distances.put(vertex, Integer.MAX_VALUE);
            predecessors.put(vertex, null);
            vertices.add(vertex);
            auxVertex = auxVertex.getNext();
        }
        distances.put(start, 0);

        // Algoritmo de Dijkstra
        while (!vertices.isEmpty()) {
            // Encuentra el vértice con la distancia mínima
            Vertex<E> current = vertices.get(0);
            for (Vertex<E> vertex : vertices) {
                if (distances.get(vertex) < distances.get(current)) {
                    current = vertex;
                }
            }

            vertices.remove(current);

            if (current.equals(end)) {
                break;
            }

            Node<Edge<E>> adjNode = current.listAdj.getFirst();
            while (adjNode != null) {
                Edge<E> edge = adjNode.getData();
                Vertex<E> neighbor = edge.getRefDest();
                int newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                }

                adjNode = adjNode.getNext();
            }
        }

        // Reconstrucción del camino
        ArrayList<E> path = new ArrayList<>();
        for (Vertex<E> at = end; at != null; at = predecessors.get(at)) {
            path.add(at.getData());
        }
        Collections.reverse(path);
        return path;
    }

    public Stack<E> Dijkstra(E startVertex, E endVertex) {
        ArrayList<E> path = shortPath(startVertex, endVertex);
        Stack<E> stack = new Stack<>();
        if (path != null) {
            for (E vertex : path) {
                stack.push(vertex);
            }
        }
        return stack;
    }

    public E searchVertex(E vertex) {
        Vertex<E> data = new Vertex<E>(vertex);
        int foundVertex = this.listVertex.search(data);

        if (foundVertex == -1) {
            return null;
        }

        return this.listVertex.searchObj(data).getData();
    }

    public boolean searchEdge(E origen, E destino) {
        Vertex<E> origenVertex = this.listVertex.searchObj(new Vertex<E>(origen));
        Vertex<E> destinoVertex = this.listVertex.searchObj(new Vertex<E>(destino));
        Edge<E> destinoEdge = new Edge<>(destinoVertex);
        if (origenVertex == null) {
            return false;
        }
        if (destinoVertex == null) {
            return false;
        } else {
            return origenVertex.listAdj.search(destinoEdge) != -1;
        }

    }

    public void removeVertex(E v) {
        Vertex<E> vertexToRemove = this.listVertex.searchObj(new Vertex<E>(v));

        if (vertexToRemove == null) {
            System.out.println("El vertice no existe");
            return;
        }

        // Remove all edges to this vertex
        Node<Edge<E>> auxEdge = vertexToRemove.getListAdj().getFirst();
        for (; auxEdge != null; auxEdge = auxEdge.getNext()) {
            Edge<E> e = auxEdge.getData();
            Vertex<E> w = e.getRefDest();
            w.getListAdj().remove(new Edge<E>(vertexToRemove));
        }

        this.listVertex.remove(vertexToRemove);
        System.out.println("Vertex removido");
    }

    public void removeEdge(E v, E z) {
        Vertex<E> origenVertex = this.listVertex.searchObj(new Vertex<E>(v));
        Vertex<E> destinoVertex = this.listVertex.searchObj(new Vertex<E>(z));

        if (origenVertex == null || destinoVertex == null) {
            System.out.println("uno de los dos vertices no existe");
            return;
        }

        Edge<E> edgeToRemove = new Edge<>(destinoVertex);
        origenVertex.listAdj.remove(edgeToRemove);
        System.out.println("Edge removed.");
    }

    public void dfs(E startVertex) {
        Vertex<E> start = this.listVertex.searchObj(new Vertex<E>(startVertex));

        if (start == null) {
            System.out.println("Start vertex does not exist.");
            return;
        }

        Stack<Vertex<E>> stack = new Stack<>();
        ListLinked<Vertex<E>> visited = new ListLinked<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            Vertex<E> current = stack.pop();

            if (visited.search(current) == -1) {
                System.out.println(current.getData());
                visited.insertLast(current);

                Node<Edge<E>> adjNode = current.listAdj.getFirst();
                while (adjNode != null) {
                    Edge<E> edge = adjNode.getData();
                    if (visited.search(edge.getRefDest()) == -1) {
                        stack.push(edge.getRefDest());
                    }
                    adjNode = adjNode.getNext();
                }
            }
        }
    }

    public void bfs(E startVertex) throws ExceptionIsEmpty {
        Vertex<E> start = this.listVertex.searchObj(new Vertex<E>(startVertex));

        if (start == null) {
            System.out.println("Start vertex does not exist.");
            return;
        }

        Queue<Vertex<E>> queue = new QueueLink<>();
        ListLinked<Vertex<E>> visited = new ListLinked<>();

        queue.enqueue(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.dequeue();

            if (visited.search(current) == -1) {
                System.out.println(current.getData());
                visited.insertLast(current);

                Node<Edge<E>> adjNode = current.listAdj.getFirst();
                while (adjNode != null) {
                    Edge<E> edge = adjNode.getData();
                    if (visited.search(edge.getRefDest()) == -1) {
                        queue.enqueue(edge.getRefDest());
                    }
                    adjNode = adjNode.getNext();
                }
            }
        }
    }

    public String toString() {
        return listVertex.toString();
    }

}
