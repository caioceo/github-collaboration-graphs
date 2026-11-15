package br.pucminas.grafos.model.graph;

import java.util.*;

import br.pucminas.grafos.model.graph.AdjacencyListGraph.No.ListNo; 

public class AdjacencyListGraph extends AbstractGraph {

    public ListNo [] adjacencyList;
    
    public AdjacencyListGraph(int numVertex){
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

        public ListNo(int edgeWeight, int vertex){
            this.top= new No(edgeWeight, vertex);
        }
    }

    @Override 
    public int getVertexInDegree(int u)throws Exception{
        if hasVertex(u)
    };
    public int getVertexOutDegree(int u)throws Exception{return 0;};    
  
}
