package br.pucminas.grafos.model.graph;

import java.util.*; 


public abstract class AbstractGraph {

    public static final String MSG1_EXCEPTION = "There is no edge between these vertices.";
    public static final String MSG2_EXCEPTION = "The vertex you are trying to access does not exist.";
    public static final String MSG3_EXCEPTION = "This edge has already been created.";
    public static final String MSG4_EXCEPTION = "There is no such edge.";

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
    public int getVertexInDegree(int u)throws Exception{return 0;};
    public int getVertexOutDegree(int u)throws Exception{return 0;};    
    public double getVertexWeight(int v)throws Exception{return listVertexWeight[v];};
    public double getEdgeWeight(int u, int v)throws Exception {return 1;};

    public void setEdgeWeight(int u, int v, double w) throws Exception {}
    public void setVertexWeight(int v, double w)throws Exception{ listVertexWeight[v] = w; };

    public boolean hasEdge(int u, int v)throws Exception{return true;};
    public boolean hasVertex(int u)throws Exception{return true;};

    public void addEdge(int u, int v)throws Exception{};
    
    public void removeEdge(int u, int v) throws Exception {}; 


    public boolean isSuccessor(int u, int v)throws Exception{return true;}; 
    public boolean isPredecessor(int u, int v)throws Exception{return true;};
    public boolean isDivergent(int u1, int v1, int u2, int v2)throws Exception{return true;}; 
    public boolean isConvergent(int u1, int v1, int u2, int v2)throws Exception{return true;};
    public boolean isIncident(int u, int v, int x)throws Exception{return true;};
    public boolean isConnected()throws Exception{return true;};
    public boolean isEmptyGraph()throws Exception{return true;};
    public boolean isCompleteGraph()throws Exception{return true;};

    void exportToGEPHI(String path)throws Exception{}; 

}