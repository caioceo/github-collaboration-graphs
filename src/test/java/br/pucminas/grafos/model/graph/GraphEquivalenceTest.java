package br.pucminas.grafos.model.graph;

import java.util.Random;
import java.util.concurrent.Callable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifica que AdjacencyMatrixGraph e AdjacencyListGraph têm comportamento
 * externo equivalente (enunciado, seção 2.1): mesmas respostas, mesmos
 * contadores e mesmas exceções para as mesmas operações.
 */
public class GraphEquivalenceTest {

    private void printHeader(String title) {
        System.out.println("\n==================================================");
        System.out.println(">>> TESTE: " + title);
        System.out.println("==================================================");
    }

    // -------------------------------------------------------------
    // Equivalência entre AdjacencyMatrixGraph e AdjacencyListGraph
    // -------------------------------------------------------------

    /** Executa a operação e devolve o resultado como texto (ou a mensagem da exceção). */
    private static String outcome(Callable<?> operation) {
        try {
            return "OK:" + operation.call();
        } catch (Exception e) {
            return "EXCEPTION:" + e.getMessage();
        }
    }

    private void assertSameState(AdjacencyMatrixGraph matrix, AdjacencyListGraph list, int n) throws Exception {
        assertEquals(matrix.getVertexCount(), list.getVertexCount());
        assertEquals(matrix.getEdgeCount(), list.getEdgeCount());
        assertEquals(matrix.isEmptyGraph(), list.isEmptyGraph());
        for (int u = 0; u < n; u++) {
            assertEquals(matrix.getVertexInDegree(u), list.getVertexInDegree(u), "grau de entrada de " + u);
            assertEquals(matrix.getVertexOutDegree(u), list.getVertexOutDegree(u), "grau de saída de " + u);
            assertEquals(matrix.getVertexWeight(u), list.getVertexWeight(u), 0.0001, "peso do vértice " + u);
            for (int v = 0; v < n; v++) {
                assertEquals(matrix.hasEdge(u, v), list.hasEdge(u, v), "hasEdge(" + u + "," + v + ")");
                assertEquals(matrix.isSuccessor(u, v), list.isSuccessor(u, v), "isSuccessor(" + u + "," + v + ")");
                assertEquals(matrix.isPredecessor(u, v), list.isPredecessor(u, v), "isPredecessor(" + u + "," + v + ")");
                if (matrix.hasEdge(u, v)) {
                    assertEquals(matrix.getEdgeWeight(u, v), list.getEdgeWeight(u, v), 0.0001, "peso de (" + u + "," + v + ")");
                }
            }
        }
    }

    @Test
    @DisplayName("Equivalência: mesma sequência fixa de operações gera o mesmo estado nas duas representações")
    public void testEquivalenceFixedSequence() throws Exception {
        printHeader("Equivalência Matriz x Lista: sequência fixa");
        int n = 5;
        AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph(n);
        AdjacencyListGraph list = new AdjacencyListGraph(n);

        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {2, 0}, {3, 4}, {4, 3}, {1, 3}};
        for (int[] e : edges) {
            System.out.println("[INPUT] addEdge(" + e[0] + ", " + e[1] + ") nas duas representações");
            matrix.addEdge(e[0], e[1]);
            list.addEdge(e[0], e[1]);
        }
        matrix.setEdgeWeight(0, 2, 3.5);
        list.setEdgeWeight(0, 2, 3.5);
        matrix.setVertexWeight(4, 8.0);
        list.setVertexWeight(4, 8.0);
        assertSameState(matrix, list, n);

        System.out.println("[INPUT] removeEdge(0, 1) e removeEdge(4, 3) nas duas representações");
        matrix.removeEdge(0, 1);
        list.removeEdge(0, 1);
        matrix.removeEdge(4, 3);
        list.removeEdge(4, 3);
        assertSameState(matrix, list, n);
        System.out.println("[OUTPUT] Estados idênticos: arestas, pesos, graus e predicados.");
    }

    @Test
    @DisplayName("Equivalência: operações inválidas lançam a mesma exceção nas duas representações")
    public void testEquivalenceOfExceptions() throws Exception {
        printHeader("Equivalência Matriz x Lista: exceções");
        AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph(3);
        AdjacencyListGraph list = new AdjacencyListGraph(3);
        matrix.addEdge(0, 1);
        list.addEdge(0, 1);

        assertEquals(outcome(() -> { matrix.addEdge(0, 1); return null; }), outcome(() -> { list.addEdge(0, 1); return null; }));
        assertEquals(outcome(() -> { matrix.addEdge(2, 2); return null; }), outcome(() -> { list.addEdge(2, 2); return null; }));
        assertEquals(outcome(() -> { matrix.addEdge(-1, 1); return null; }), outcome(() -> { list.addEdge(-1, 1); return null; }));
        assertEquals(outcome(() -> { matrix.addEdge(0, 3); return null; }), outcome(() -> { list.addEdge(0, 3); return null; }));
        assertEquals(outcome(() -> { matrix.removeEdge(1, 0); return null; }), outcome(() -> { list.removeEdge(1, 0); return null; }));
        assertEquals(outcome(() -> { matrix.removeEdge(0, 9); return null; }), outcome(() -> { list.removeEdge(0, 9); return null; }));
        assertEquals(outcome(() -> matrix.getEdgeWeight(1, 2)), outcome(() -> list.getEdgeWeight(1, 2)));
        assertEquals(outcome(() -> matrix.getEdgeWeight(7, 2)), outcome(() -> list.getEdgeWeight(7, 2)));
        assertEquals(outcome(() -> { matrix.setEdgeWeight(1, 2, 2.0); return null; }), outcome(() -> { list.setEdgeWeight(1, 2, 2.0); return null; }));
        assertEquals(outcome(() -> matrix.hasEdge(-1, 0)), outcome(() -> list.hasEdge(-1, 0)));
        assertEquals(outcome(() -> matrix.getVertexInDegree(3)), outcome(() -> list.getVertexInDegree(3)));
        assertEquals(outcome(() -> matrix.getVertexOutDegree(-1)), outcome(() -> list.getVertexOutDegree(-1)));
        assertEquals(outcome(() -> matrix.getVertexWeight(3)), outcome(() -> list.getVertexWeight(3)));
        assertEquals(outcome(() -> { matrix.setVertexWeight(-1, 1.0); return null; }), outcome(() -> { list.setVertexWeight(-1, 1.0); return null; }));
        assertEquals(outcome(() -> new AdjacencyMatrixGraph(0)), outcome(() -> new AdjacencyListGraph(0)).replace("AdjacencyListGraph", "AdjacencyMatrixGraph"));
        System.out.println("[OUTPUT] Mesmas mensagens de exceção em todos os casos inválidos.");
    }

    @Test
    @DisplayName("Equivalência: 3000 operações aleatórias (semente fixa) mantêm as duas representações idênticas")
    public void testEquivalenceRandomOperations() throws Exception {
        printHeader("Equivalência Matriz x Lista: operações aleatórias (seed 42)");
        int n = 6;
        AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph(n);
        AdjacencyListGraph list = new AdjacencyListGraph(n);
        Random rnd = new Random(42);

        for (int step = 0; step < 3000; step++) {
            int op = rnd.nextInt(6);
            int u = rnd.nextInt(n + 2) - 1; // inclui -1 e n (inválidos)
            int v = rnd.nextInt(n + 2) - 1;
            double w = rnd.nextInt(100) / 4.0;

            String expected;
            String actual;
            switch (op) {
                case 0 -> {
                    expected = outcome(() -> { matrix.addEdge(u, v); return null; });
                    actual = outcome(() -> { list.addEdge(u, v); return null; });
                }
                case 1 -> {
                    expected = outcome(() -> { matrix.removeEdge(u, v); return null; });
                    actual = outcome(() -> { list.removeEdge(u, v); return null; });
                }
                case 2 -> {
                    expected = outcome(() -> { matrix.setEdgeWeight(u, v, w); return null; });
                    actual = outcome(() -> { list.setEdgeWeight(u, v, w); return null; });
                }
                case 3 -> {
                    expected = outcome(() -> matrix.getEdgeWeight(u, v));
                    actual = outcome(() -> list.getEdgeWeight(u, v));
                }
                case 4 -> {
                    expected = outcome(() -> { matrix.setVertexWeight(u, w); return null; });
                    actual = outcome(() -> { list.setVertexWeight(u, w); return null; });
                }
                default -> {
                    expected = outcome(() -> matrix.getVertexInDegree(u) + "/" + matrix.getVertexOutDegree(u));
                    actual = outcome(() -> list.getVertexInDegree(u) + "/" + list.getVertexOutDegree(u));
                }
            }
            assertEquals(expected, actual, "divergência no passo " + step + " (op=" + op + ", u=" + u + ", v=" + v + ")");
        }

        assertSameState(matrix, list, n);
        System.out.println("[OUTPUT] 3000 operações executadas sem divergência. Arestas finais: " + list.getEdgeCount());
    }
}
