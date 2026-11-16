package br.pucminas.grafos.model.graph;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


import java.util.*;

public class AdjacencyListGraph extends AbstractGraph {
    private List<Map<Integer, Double>> list; // lista de adjacencia com mapa V_adjacente -> Peso para cada vertice da lista
        } 

    
    public ListNo [] adjacencyList;
    
    public AdjacencyListGraph(int numVertex) throws Exception{
        super(numVertex);
        this.adjacencyList = new ListNo[numVertex];
    }

    public class No{
        public int edgeWeight;
        public int vertex;
        public No prox;

        public No(int edgeWeight, int vertex){
            this.edgeWeight = edgeWeight;
            this.vertex = vertex;
            this.prox = null; 
        }
    }

    public class  ListNo {
        public No top;
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

        public ListNo(int edgeWeight, int vertex){
            this.top= new No(edgeWeight, vertex);
        }
    }



}
