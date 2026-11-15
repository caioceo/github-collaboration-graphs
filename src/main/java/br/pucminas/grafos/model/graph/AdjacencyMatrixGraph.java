package br.pucminas.grafos.model.graph;

/** Grafo representado por matriz de adjacência (T1.2). */
public class AdjacencyMatrixGraph extends AbstractGraph {

    public AdjacencyMatrixGraph(int numVertices) {
        super(numVertices);
        // TODO T1.2: criar a matriz (ex.: double[][] ou boolean[][] + double[][] para os pesos)
    }

    @Override
    protected boolean containsEdge(int u, int v) {
        throw new UnsupportedOperationException("TODO T1.2");
    }

    @Override
    protected void insertEdge(int u, int v, double weight) {
        throw new UnsupportedOperationException("TODO T1.2");
    }

    @Override
    protected void deleteEdge(int u, int v) {
        throw new UnsupportedOperationException("TODO T1.2");
    }

    @Override
    protected double readEdgeWeight(int u, int v) {
        throw new UnsupportedOperationException("TODO T1.2");
    }

    @Override
    protected void writeEdgeWeight(int u, int v, double weight) {
        throw new UnsupportedOperationException("TODO T1.2");
    }
}
