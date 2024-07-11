package graph;

class Edge<E> {

    private Vertex<E> refDest;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge(Vertex<E> refDest) {
        this.refDest = refDest;
    }

    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    public Vertex<E> getRefDest() {
        return refDest;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return refDest.equals(edge.refDest);
    }

    @Override
    public int hashCode() {
        return refDest.hashCode();
    }

    public String toString() {
        if(this.weight>-1) return refDest.getData()+" ["+this.weight+"], ";
        return refDest.getData()+", ";
    }
}