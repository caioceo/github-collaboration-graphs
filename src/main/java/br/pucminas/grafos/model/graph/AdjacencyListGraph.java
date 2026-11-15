package br.pucminas.grafos.model.graph;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


/** Grafo representado por listas de adjacência (T1.3). */
public class AdjacencyListGraph extends AbstractGraph {
    private List<Map<Integer, Double>> list; // lista de adjacencia com mapa V_adjacente -> Peso para cada vertice da lista
        } 

    public AdjacencyListGraph(int numVertices) {
        super(numVertices);
        list = new ArrayList<>(); // Inicializa a lista de adjacência vazia
        for (int i = 0; i < numVertices; i++) {
            list.add(new HashMap<>()); // Inicializa um mapa V_adjacente -> Peso para cada vertice da lista
        } 
    }   

    @Override
    protected boolean containsEdge(int u, int v) {
        return list.get(u).containsKey(v); 
    }

    @Override
    protected void insertEdge(int u, int v, double weight) {
        list.get(u).put(v, weight);}

    @Override
    protected void deleteEdge(int u, int v) {
        list.get(u).remove(v);
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
