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
      System.out.println(" | ");
    }
  }


  @Override 
  void addEdge(int u, int v){
    adjacencyMatrix[u][v] = DEFAULT_EDGE_WEIGHT;
  }

}


