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


    public int getVertexCount(){return numVertex;};
    public int getEdgeCount(){return edgeCount;};
    public int getVertexInDegree(int u){return 0;};
    public int getVertexOutDegree(int u){return 0;};    
    public double getVertexWeight(int v){return listVertexWeight[v];};
    public double getEdgeWeight(int u, int v){return 1;};

    public void setEdgeWeight(int u, int v, double w){}

    public void setVertexWeight(int v, double w){ listVertexWeight[v] = w; };

    public boolean hasEdge(int u, int v){return true;};

    public void addEdge(int u, int v){};
    
    public void removeEdge(int u, int v){}; 


    protected boolean isSuccessor(int u, int v){return true;}; 
    protected boolean isPredecessor(int u, int v){return true;};
    protected boolean isDivergent(int u1, int v1, int u2, int v2){return true;}; 
    protected boolean isConvergent(int u1, int v1, int u2, int v2){return true;};
    protected boolean isIncident(int u, int v, int x){return true;};
    protected boolean isConnected(){return true;};
    protected boolean isEmptyGraph(){return true;};
    protected boolean isCompleteGraph(){return true;};

    void exportToGEPHI(String path){}; 

}