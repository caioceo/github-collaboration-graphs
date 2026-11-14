package br.pucminas.grafos.model.graph;

import java.util.*; 

public class AdjacencyMatrixGraph extends AbstractGraph{

  public Double [][] adjacencyMatrix; 

  public AdjacencyMatrixGraph(int numVertex){
      super(numVertex);
      this.adjacencyMatrix = new Double[this.numVertex][this.numVertex];
  }

    
  public void viewMatrix(){
    numVertex = getVertexCount();
    for(int i = 0 ; i < numVertex ; i++ ){
      System.out.print(" | ");
        for(int j = 0 ; j < numVertex ; j++){
          System.out.print(adjacencyMatrix[i][j] + " | ");
        }
      System.out.println();
    }
  }

  
  @Override 
  public int getVertexInDegree(int u)throws Exception{
    int temp = 0; 
    try{
      hasVertex(u);
      for(int i = 0; i < this.numVertex; i++){
        if(i!= u && hasEdge(i, u)){
          temp++;            
        }
      }
       return  temp;
    }catch(Exception e){
      throw new Exception(e);
    }
  };
  
  @Override
  public int getVertexOutDegree(int u)throws Exception{
    int temp = 0; 
    try{
      hasVertex(u);
      for(int i = 0; i < this.numVertex; i++){
        if(i!= u && hasEdge(u, i )){
          temp++;            
        }
      }
       return temp;
    }catch(Exception e){
      throw new Exception(e);
    }
  };  

  @Override 
  public double getVertexWeight(int v)throws Exception{
    try{
       hasVertex(v);
       return super.getVertexWeight(v);
    }catch(Exception e){
      throw new Exception(e); 
    }
  };

  @Override 
  public double getEdgeWeight(int u, int v) throws Exception{
    double temp = 0 ; 
    if(hasEdge(u, v)){
      temp = adjacencyMatrix[u][v];
    }else{
      throw new Exception(MSG1_EXCEPTION);
    }
    return temp;
  } 
  
  @Override 
  public void setEdgeWeight(int u, int v, double w) throws Exception {
    if(hasEdge(u, v)){
        adjacencyMatrix[u][v] = w; 
    }else{
      throw new Exception(MSG1_EXCEPTION);
    }
  }
  
  @Override 
  public void addEdge(int u, int v)throws Exception{ 
    if(hasEdge(u, v)){
      throw new Exception(MSG3_EXCEPTION);
    }else{
      adjacencyMatrix[u][v] = DEFAULT_EDGE_WEIGHT;
    } 
  }

  @Override 
  public boolean hasEdge(int u, int v)throws Exception{
    boolean temp = false;
    if(hasVertex(u) && hasVertex(v)){
      temp = adjacencyMatrix[u][v] == null ? false : true;
    }else{
      throw new Exception(MSG2_EXCEPTION);
    }
    return temp;
  };
  
  
  @Override 
  public boolean hasVertex(int u) throws Exception{ 
    boolean temp = false;
    if(numVertex > u && u >= 0){
      temp = true;
    }else{
       throw new Exception(MSG2_EXCEPTION);
    }
    return temp;
  };


  @Override 
  public void removeEdge(int u, int v) throws Exception{
    if(hasEdge(u, v)){
      adjacencyMatrix[u][v] = null;
    }else{
      throw new Exception(MSG4_EXCEPTION);
    }
    
  };


  @Override 
  public boolean isSuccessor(int u, int v)throws Exception{
    return hasEdge(u, v);
  }; 
  
  @Override 
  public boolean isPredecessor(int u, int v)throws Exception{
    return hasEdge(v, u);
  };
  
  @Override 
  public boolean isDivergent(int u1, int v1, int u2, int v2)throws Exception{return true;}; 
  
  @Override 
  public boolean isConvergent(int u1, int v1, int u2, int v2)throws Exception{return true;};
  
  @Override 
  public boolean isIncident(int u, int v, int x)throws Exception{return true;};
  
  @Override 
  public boolean isConnected()throws Exception{return true;};
  
  @Override 
  public boolean isEmptyGraph()throws Exception{return true;};
  
  @Override 
  public boolean isCompleteGraph()throws Exception{return true;};





}


