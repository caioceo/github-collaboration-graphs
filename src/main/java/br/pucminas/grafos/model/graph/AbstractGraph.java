package br.pucminas.grafos.model.graph;

import java.util.*; 


public abstract class AbstractGraph {

    public static final String MSG1_EXCEPTION = "There is no edge between these vertices.";
    public static final String MSG2_EXCEPTION = "The vertex you are trying to access does not exist.";
    public static final String MSG3_EXCEPTION = "This edge has already been created.";
    public static final String MSG4_EXCEPTION = "There is no such edge.";
    public static final String MSG5_EXCEPTION = "It is not possible to create a graph with fewer than one vertex.";
    public static final String MSG6_EXCEPTION = "Self-loops are not allowed.";

    public static final double DEFAULT_VERTEX_WEIGHT = 0.0;
    public static final double DEFAULT_EDGE_WEIGHT = 1.0;

    protected int numVertex;
    protected int edgeCount; 
    protected double [] listVertexWeight; 

    public AbstractGraph(int numVertex)throws Exception{
        if(numVertex > 0 ){
            this.numVertex = numVertex;
            this.listVertexWeight = new double[numVertex];
            Arrays.fill(this.listVertexWeight, DEFAULT_VERTEX_WEIGHT);
        }else{
            throw new Exception(MSG5_EXCEPTION);
        }  
    }


    public int getVertexCount(){return numVertex;};
    public int getEdgeCount(){return edgeCount;};
    public int getVertexInDegree(int u)throws Exception{return 0;};
    public int getVertexOutDegree(int u)throws Exception{return 0;};    
    public double getVertexWeight(int v)throws Exception{
        if(!hasVertex(v)){
            throw new Exception(MSG2_EXCEPTION);
        }
        return listVertexWeight[v];
    };
    public double getEdgeWeight(int u, int v)throws Exception {return 1;};

    public void setEdgeWeight(int u, int v, double w) throws Exception {}
    public void setVertexWeight(int v, double w)throws Exception{
        if(!hasVertex(v)){
            throw new Exception(MSG2_EXCEPTION);
        }
        listVertexWeight[v] = w;
    };

    public boolean hasEdge(int u, int v)throws Exception{return true;};
    public boolean hasVertex(int u)throws Exception{
        return u >= 0 && u < numVertex;
    };

    public void addEdge(int u, int v)throws Exception{};
    
    public void removeEdge(int u, int v) throws Exception {}; 


    public boolean isSuccessor(int u, int v)throws Exception{ return hasEdge(u, v);}; 
    public boolean isPredecessor(int u, int v)throws Exception{ return hasEdge(v,u);};
    public boolean isDivergent(int u1, int v1, int u2, int v2)throws Exception{return u1 == u2 && v1 != v2 ;}; 
    public boolean isConvergent(int u1, int v1, int u2, int v2)throws Exception{return u1 != u2 && v1 == v2 ;};
    public boolean isIncident(int u, int v, int x)throws Exception{return x==u || x==v;};
    public boolean isConnected()throws Exception{return this.edgeCount >= this.numVertex-1;};
    public boolean isEmptyGraph()throws Exception{return this.edgeCount==0 ;};
    public boolean isCompleteGraph()throws Exception{return  this.edgeCount == this.numVertex*this.numVertex/2; };

    void exportToGEPHI(String path)throws Exception{}; 

}
