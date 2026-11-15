package br.pucminas.grafos.model.graph;

/** API comum da biblioteca de grafos (T1.1). Esqueleto para implementação. */
public abstract class AbstractGraph {

    // TODO T1.1: atributos compartilhados (número de vértices, pesos dos vértices, contador de arestas)
    // TODO T1.1: pesos iniciais de vértices e arestas (definir e documentar)

    protected AbstractGraph(int numVertices) {
        // TODO T1.1
    }

    // Métodos que as classes concretas implementam (já sobrescritos em
    // AdjacencyMatrixGraph e AdjacencyListGraph; renomeie ou troque se quiser).

    protected abstract boolean containsEdge(int u, int v);

    protected abstract void insertEdge(int u, int v, double weight);

    protected abstract void deleteEdge(int u, int v);

    protected abstract double readEdgeWeight(int u, int v);

    protected abstract void writeEdgeWeight(int u, int v, double weight);

    // API obrigatória (seção 2.3 do enunciado)

    public int getVertexCount() {
        throw new UnsupportedOperationException("TODO");
    }

    public int getEdgeCount() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean hasEdge(int u, int v) {
        throw new UnsupportedOperationException("TODO");
    }

    public void addEdge(int u, int v) {
        throw new UnsupportedOperationException("TODO");
    }

    public void removeEdge(int u, int v) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isSuccessor(int u, int v) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isPredecessor(int u, int v) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isDivergent(int u1, int v1, int u2, int v2) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isConvergent(int u1, int v1, int u2, int v2) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isIncident(int u, int v, int x) {
        throw new UnsupportedOperationException("TODO");
    }

    public int getVertexInDegree(int u) {
        throw new UnsupportedOperationException("TODO");
    }

    public int getVertexOutDegree(int u) {
        throw new UnsupportedOperationException("TODO");
    }

    public void setVertexWeight(int v, double w) {
        throw new UnsupportedOperationException("TODO");
    }

    public double getVertexWeight(int v) {
        throw new UnsupportedOperationException("TODO");
    }

    public void setEdgeWeight(int u, int v, double w) {
        throw new UnsupportedOperationException("TODO");
    }

    public double getEdgeWeight(int u, int v) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isConnected() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmptyGraph() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isCompleteGraph() {
        throw new UnsupportedOperationException("TODO");
    }

    public void exportToGEPHI(String path) {
        throw new UnsupportedOperationException("TODO");
    }
}
