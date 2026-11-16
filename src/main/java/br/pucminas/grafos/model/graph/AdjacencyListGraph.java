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
    public boolean hasEdge(int u, int v) throws Exception {
        return list.get(u).containsKey(v); 
    }

    @Override
    public void addEdge(int u, int v) throws Exception{
        list.get(u).put(v, null);}

    @Override
    public void removeEdge(int u, int v) throws Exception{
        list.get(u).remove(v);
    }

    @Override
    public double getVertexWeight(int u, int v)  throws Exception {
        throw new UnsupportedOperationException("TODO T1.3");
    }

    @Override
    public void writeEdgeWeight(int u, int v, double weight)  throws Exception {
        throw new UnsupportedOperationException("TODO T1.3");
    }
}
