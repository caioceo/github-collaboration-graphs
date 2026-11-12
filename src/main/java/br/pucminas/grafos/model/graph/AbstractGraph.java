package br.pucminas.grafos.model.graph;

import java.util.*; 


public abstract class AbstractGraph {


    public static final double DEFAULT_VERTEX_WEIGHT = 0.0;
    public static final double DEFAULT_EDGE_WEIGHT = 1.0;

    protected int numVertex;
    protected int edgeCount; 
    protected double [] listVertexWeight; 

    public AbstractGraph(int numVertex){
        this.numVertex = numVertex;
    }


    int getVertexCount(){return numVertex;};
    int getEdgeCount(){return edgeCount;};
    int getVertexInDegree(int u){return 1;};
    int getVertexOutDegree(int u){return 1;};    
    
    double getVertexWeight(int v){
        return listVertexWeight[v];
    };
    double getEdgeWeight(int u, int v){return 1;};

    void setEdgeWeight(int u, int v, double w){}
    void setVertexWeight(int v, double w){};
    boolean hasEdge(int u, int v){return true;};

    void addEdge(int u, int v){};
    void removeEdge(int u, int v){}; 



    boolean isSuccessor(int u, int v){return true;}; 
    boolean isPredecessor(int u, int v){return true;};
    boolean isDivergent(int u1, int v1, int u2, int v2){return true;}; 
    boolean isConvergent(int u1, int v1, int u2, int v2){return true;};
    boolean isIncident(int u, int v, int x){return true;};
    boolean isConnected(){return true;};
    boolean isEmptyGraph(){return true;};
    boolean isCompleteGraph(){return true;};

    void exportToGEPHI(String path){}; 

    
}