package br.pucminas.grafos.model.graph;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Locale;
import java.util.Queue;

public abstract class AbstractGraph {

    public static final double DEFAULT_VERTEX_WEIGHT = 0.0;
    public static final double DEFAULT_EDGE_WEIGHT = 1.0;

    protected final int numVertices;
    protected final double[] vertexWeights;
    private int edgeCount;

    protected AbstractGraph(int numVertices) {
        if (numVertices <= 0) {
            throw new IllegalArgumentException("O grafo precisa ter pelo menos 1 vértice.");
        }
        this.numVertices = numVertices;
        this.vertexWeights = new double[numVertices];
        Arrays.fill(vertexWeights, DEFAULT_VERTEX_WEIGHT);
    }

    protected abstract boolean containsEdge(int u, int v);

    protected abstract void insertEdge(int u, int v, double weight);

    protected abstract void deleteEdge(int u, int v);

    protected abstract double readEdgeWeight(int u, int v);

    protected abstract void writeEdgeWeight(int u, int v, double weight);

    protected void validateVertex(int v) {
        if (v < 0 || v >= numVertices) {
            throw new IndexOutOfBoundsException(
                    "Vértice inválido: " + v + " (válidos: 0 a " + (numVertices - 1) + ").");
        }
    }

    private void validateExistingEdge(int u, int v) {
        if (!hasEdge(u, v)) {
            throw new IllegalStateException("A aresta (" + u + ", " + v + ") não existe.");
        }
    }

    public int getVertexCount() {
        return numVertices;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public int getVertexInDegree(int u) {
        validateVertex(u);
        int degree = 0;
        for (int w = 0; w < numVertices; w++) {
            if (containsEdge(w, u)) {
                degree++;
            }
        }
        return degree;
    }

    public int getVertexOutDegree(int u) {
        validateVertex(u);
        int degree = 0;
        for (int w = 0; w < numVertices; w++) {
            if (containsEdge(u, w)) {
                degree++;
            }
        }
        return degree;
    }

    public double getVertexWeight(int v) {
        validateVertex(v);
        return vertexWeights[v];
    }

    public final double getEdgeWeight(int u, int v) {
        validateExistingEdge(u, v);
        return readEdgeWeight(u, v);
    }

    public void setVertexWeight(int v, double w) {
        validateVertex(v);
        vertexWeights[v] = w;
    }

    public final void setEdgeWeight(int u, int v, double w) {
        validateExistingEdge(u, v);
        writeEdgeWeight(u, v, w);
    }

    public boolean isSuccessor(int u, int v) {
        return hasEdge(u, v);
    }

    public boolean isPredecessor(int u, int v) {
        return hasEdge(u, v);
    }

    public boolean isDivergent(int u1, int v1, int u2, int v2) {
        return hasEdge(u1, v1) && hasEdge(u2, v2) && u1 == u2;
    }

    public boolean isConvergent(int u1, int v1, int u2, int v2) {
        return hasEdge(u1, v1) && hasEdge(u2, v2) && v1 == v2;
    }

    public boolean isIncident(int u, int v, int x) {
        validateVertex(x);
        return hasEdge(u, v) && (x == u || x == v);
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[0] = true;
        queue.add(0);
        int reached = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int w = 0; w < numVertices; w++) {
                if (!visited[w] && (containsEdge(current, w) || containsEdge(w, current))) {
                    visited[w] = true;
                    reached++;
                    queue.add(w);
                }
            }
        }
        return reached == numVertices;
    }

    public boolean isEmptyGraph() {
        return edgeCount == 0;
    }

    public boolean isCompleteGraph() {
        return edgeCount == (long) numVertices * (numVertices - 1);
    }

    public final boolean hasEdge(int u, int v) {
        validateVertex(u);
        validateVertex(v);
        return containsEdge(u, v);
    }

    public final void addEdge(int u, int v) {
        validateVertex(u);
        validateVertex(v);
        if (u == v) {
            throw new IllegalArgumentException("Laços não são permitidos: (" + u + ", " + u + ").");
        }
        if (containsEdge(u, v)) {
            return;
        }
        insertEdge(u, v, DEFAULT_EDGE_WEIGHT);
        edgeCount++;
    }

    public final void removeEdge(int u, int v) {
        validateExistingEdge(u, v);
        deleteEdge(u, v);
        edgeCount--;
    }

    public void exportToGEPHI(String path) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<gexf xmlns=\"http://gexf.net/1.3\" version=\"1.3\">\n");
        xml.append("  <graph defaultedgetype=\"directed\">\n");
        xml.append("    <attributes class=\"node\">\n");
        xml.append("      <attribute id=\"weight\" title=\"weight\" type=\"double\"/>\n");
        xml.append("    </attributes>\n");

        xml.append("    <nodes>\n");
        for (int v = 0; v < numVertices; v++) {
            xml.append(String.format(Locale.ROOT,
                    "      <node id=\"%d\" label=\"%d\"><attvalues><attvalue for=\"weight\" value=\"%s\"/></attvalues></node>%n",
                    v, v, vertexWeights[v]));
        }
        xml.append("    </nodes>\n");

        xml.append("    <edges>\n");
        int id = 0;
        for (int u = 0; u < numVertices; u++) {
            for (int v = 0; v < numVertices; v++) {
                if (containsEdge(u, v)) {
                    xml.append(String.format(Locale.ROOT,
                            "      <edge id=\"%d\" source=\"%d\" target=\"%d\" weight=\"%s\"/>%n",
                            id++, u, v, readEdgeWeight(u, v)));
                }
            }
        }
        xml.append("    </edges>\n");
        xml.append("  </graph>\n");
        xml.append("</gexf>\n");

        try {
            Path file = Path.of(path);
            if (file.getParent() != null) {
                Files.createDirectories(file.getParent());
            }
            Files.writeString(file, xml, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Não foi possível exportar para " + path, e);
        }
    }
}
