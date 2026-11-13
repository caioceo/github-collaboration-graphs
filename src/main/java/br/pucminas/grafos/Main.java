package br.pucminas.grafos;
import br.pucminas.grafos.model.graph.AdjacencyMatrixGraph;

public class Main {

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.viewMatrix();
        System.out.println(graph.hasEdge(0, 1));
        graph.removeEdge(0, 1);
        graph.viewMatrix();
        System.out.println(graph.hasEdge(0, 1));
        graph.setEdgeWeight(1,2,10);
        graph.viewMatrix();
        

    }
}
