package br.pucminas.grafos.model.graph;

/** Grafo representado por listas de adjacência (T1.3). */
public class AdjacencyListGraph extends AbstractGraph {

    public AdjacencyListGraph(int numVertices) {
        super(numVertices);
        // TODO T1.3: criar as listas (ex.: List<Map<Integer, Double>>, destino -> peso)
    }

    @Override
    protected boolean containsEdge(int u, int v) {
        throw new UnsupportedOperationException("TODO T1.3");
    }

    @Override
    protected void insertEdge(int u, int v, double weight) {
        throw new UnsupportedOperationException("TODO T1.3");
    }

    @Override
    protected void deleteEdge(int u, int v) {
        throw new UnsupportedOperationException("TODO T1.3");
    }

    @Override
    protected double readEdgeWeight(int u, int v) {
        throw new UnsupportedOperationException("TODO T1.3");
    }

    @Override
    protected void writeEdgeWeight(int u, int v, double weight) {
        throw new UnsupportedOperationException("TODO T1.3");
    }
}
