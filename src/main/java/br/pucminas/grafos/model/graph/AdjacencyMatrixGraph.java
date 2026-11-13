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



  public double getEdgeWeight(int u, int v){ return adjacencyMatrix[u][v]; } 

  public void setEdgeWeight(int u, int v, double w){
    if(hasEdge(u, v)){
      adjacencyMatrix[u][v] = w; 
    }else{
      System.out.println("Não aresta entre esses vertices");
    }
  }

  public void addEdge(int u, int v){ 
    if(!hasEdge(u, v)){
      adjacencyMatrix[u][v] = DEFAULT_EDGE_WEIGHT;
    }else{
      System.out.println("Essa aresta ja foi criada");
    } 
  }

  public boolean hasEdge(int u, int v){ return  adjacencyMatrix[u][v] == null ? false : true;};

  public void removeEdge(int u, int v){
    if(hasEdge(u, v)){
      adjacencyMatrix[u][v] = null;
    }else{
      System.out.println("Essa aresta não existe");
    }
    
  };





}


