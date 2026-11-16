package br.pucminas.grafos.model.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Grafo representado por listas de adjacência (T1.3). */
public class AdjacencyListGraph extends AbstractGraph {

    private List<Map<Integer, Double>> list;

    public AdjacencyListGraph(int numVertices) throws Exception {
        super(numVertices);
        this.list = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            this.list.add(new HashMap<>());
        }
    }

    @Override
    public boolean hasEdge(int u, int v) throws Exception {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new Exception(MSG2_EXCEPTION);
        }
        return list.get(u).containsKey(v);
    }

    @Override
    public void addEdge(int u, int v) throws Exception {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new Exception(MSG2_EXCEPTION);
        }
        if (u == v) {
            throw new Exception(MSG6_EXCEPTION);
        }
        if (hasEdge(u, v)) {
            throw new Exception(MSG3_EXCEPTION);
        }
        list.get(u).put(v, DEFAULT_EDGE_WEIGHT);
        this.edgeCount++;
    }

    @Override
    public void removeEdge(int u, int v) throws Exception {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new Exception(MSG2_EXCEPTION);
        }
        if (!hasEdge(u, v)) {
            throw new Exception(MSG4_EXCEPTION);
        }
        list.get(u).remove(v);
        this.edgeCount--;
    }

    @Override
    public double getEdgeWeight(int u, int v) throws Exception {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new Exception(MSG2_EXCEPTION);
        }
        if (!hasEdge(u, v)) {
            throw new Exception(MSG1_EXCEPTION);
        }
        return list.get(u).get(v);
    }

    @Override
    public void setEdgeWeight(int u, int v, double w) throws Exception {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new Exception(MSG2_EXCEPTION);
        }
        if (!hasEdge(u, v)) {
            throw new Exception(MSG1_EXCEPTION);
        }
        list.get(u).put(v, w);
    }

    @Override
    public int getVertexInDegree(int u) throws Exception {
        if (!hasVertex(u)) {
            throw new Exception(MSG2_EXCEPTION);
        }
        int degree = 0;
        for (int i = 0; i < this.numVertex; i++) {
            if (i != u && list.get(i).containsKey(u)) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public int getVertexOutDegree(int u) throws Exception {
        if (!hasVertex(u)) {
            throw new Exception(MSG2_EXCEPTION);
        }
        return list.get(u).size();
    }
}
