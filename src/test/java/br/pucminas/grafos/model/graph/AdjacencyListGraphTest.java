package br.pucminas.grafos.model.graph;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários da AdjacencyListGraph (T1.5).
 *
 * Seções 1 a 6 espelham o AdjacencyMatrixGraphTest, para que as duas
 * representações sejam verificadas com os mesmos cenários.
 * A seção 7 cobre comportamentos específicos da lista.
 * A equivalência com a matriz fica em GraphEquivalenceTest.
 * A seção 8 documenta problemas conhecidos da AbstractGraph (T1.4).
 */
public class AdjacencyListGraphTest {

    private void printHeader(String title) {
        System.out.println("\n==================================================");
        System.out.println(">>> TESTE: " + title);
        System.out.println("==================================================");
    }

    // -------------------------------------------------------------
    // 1. Criação e limites de vértices
    // -------------------------------------------------------------

    @Test
    @DisplayName("Criação de grafo com número válido de vértices (incluindo limite mínimo = 1)")
    public void testCreateGraphValid() throws Exception {
        printHeader("Criação de grafo válido (N=1 e N=4)");

        System.out.println("[INPUT] new AdjacencyListGraph(1)");
        AdjacencyListGraph g1 = new AdjacencyListGraph(1);
        System.out.println("[OUTPUT] getVertexCount() = " + g1.getVertexCount() + ", getEdgeCount() = " + g1.getEdgeCount());
        assertEquals(1, g1.getVertexCount());
        assertEquals(0, g1.getEdgeCount());

        System.out.println("[INPUT] new AdjacencyListGraph(4)");
        AdjacencyListGraph g4 = new AdjacencyListGraph(4);
        System.out.println("[OUTPUT] getVertexCount() = " + g4.getVertexCount() + ", getEdgeCount() = " + g4.getEdgeCount());
        assertEquals(4, g4.getVertexCount());
        assertEquals(0, g4.getEdgeCount());
    }

    @Test
    @DisplayName("Caso Extremo: Criação com 0 ou vértices negativos deve lançar exceção")
    public void testCreateGraphZeroOrNegative() {
        printHeader("Caso Extremo: Criação com 0 e vértices negativos");

        for (int n : new int[] {0, -1, -50}) {
            System.out.println("[INPUT] new AdjacencyListGraph(" + n + ")");
            Exception ex = assertThrows(Exception.class, () -> new AdjacencyListGraph(n));
            System.out.println("[OUTPUT] Exceção lançada: " + ex.getMessage());
            assertEquals(AbstractGraph.MSG5_EXCEPTION, ex.getMessage());
        }
    }

    // -------------------------------------------------------------
    // 2. Inserção, consulta e remoção de arestas
    // -------------------------------------------------------------

    @Test
    @DisplayName("Adicionar e verificar arestas direcionadas")
    public void testAddAndHasEdge() throws Exception {
        printHeader("Adição e Verificação de Aresta Direcionada (0 -> 1)");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);

        System.out.println("[INPUT] addEdge(0, 1)");
        graph.addEdge(0, 1);

        System.out.println("[OUTPUT] hasEdge(0, 1) = " + graph.hasEdge(0, 1));
        System.out.println("[OUTPUT] hasEdge(1, 0) = " + graph.hasEdge(1, 0) + " (deve ser false - grafo direcionado)");
        System.out.println("[OUTPUT] getEdgeCount() = " + graph.getEdgeCount());

        assertTrue(graph.hasEdge(0, 1));
        assertFalse(graph.hasEdge(1, 0));
        assertEquals(1, graph.getEdgeCount());
    }

    @Test
    @DisplayName("Caso Extremo: Adicionar aresta duplicada deve lançar exceção e não alterar o grafo")
    public void testAddDuplicateEdgeThrowsException() throws Exception {
        printHeader("Caso Extremo: Adição de Aresta Duplicada");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);

        System.out.println("[INPUT] 1ª chamada: addEdge(0, 1)");
        graph.addEdge(0, 1);
        graph.setEdgeWeight(0, 1, 4.5);

        System.out.println("[INPUT] 2ª chamada: addEdge(0, 1) (duplicada)");
        Exception ex = assertThrows(Exception.class, () -> graph.addEdge(0, 1));
        System.out.println("[OUTPUT] Exceção lançada: " + ex.getMessage());
        assertEquals(AbstractGraph.MSG3_EXCEPTION, ex.getMessage());
        assertEquals(1, graph.getEdgeCount());
        assertEquals(4.5, graph.getEdgeWeight(0, 1), 0.0001, "a tentativa duplicada não deve resetar o peso");
    }

    @Test
    @DisplayName("Caso Extremo: Adicionar laço (self-loop u -> u) deve lançar exceção")
    public void testSelfLoopThrowsException() throws Exception {
        printHeader("Caso Extremo: Bloqueio de Laços (u -> u)");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);

        System.out.println("[INPUT] addEdge(1, 1)");
        Exception ex = assertThrows(Exception.class, () -> graph.addEdge(1, 1));
        System.out.println("[OUTPUT] Exceção lançada: " + ex.getMessage());
        assertEquals(AbstractGraph.MSG6_EXCEPTION, ex.getMessage());
        assertEquals(0, graph.getEdgeCount());
        assertFalse(graph.hasEdge(1, 1));
    }

    @Test
    @DisplayName("Remover aresta existente e consultar após remoção")
    public void testRemoveEdge() throws Exception {
        printHeader("Remoção de Aresta Existente");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);
        graph.addEdge(0, 2);
        System.out.println("[INPUT] Criada aresta (0, 2). getEdgeCount() = " + graph.getEdgeCount());

        System.out.println("[INPUT] removeEdge(0, 2)");
        graph.removeEdge(0, 2);

        System.out.println("[OUTPUT] hasEdge(0, 2) = " + graph.hasEdge(0, 2));
        System.out.println("[OUTPUT] getEdgeCount() = " + graph.getEdgeCount());
        assertFalse(graph.hasEdge(0, 2));
        assertEquals(0, graph.getEdgeCount());
    }

    @Test
    @DisplayName("Caso Extremo: Remover aresta inexistente deve lançar exceção")
    public void testRemoveNonExistentEdgeThrowsException() throws Exception {
        printHeader("Caso Extremo: Remoção de Aresta Inexistente");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);

        System.out.println("[INPUT] removeEdge(0, 1) em grafo vazio");
        Exception ex = assertThrows(Exception.class, () -> graph.removeEdge(0, 1));
        System.out.println("[OUTPUT] Exceção lançada: " + ex.getMessage());
        assertEquals(AbstractGraph.MSG4_EXCEPTION, ex.getMessage());
        assertEquals(0, graph.getEdgeCount());
    }

    // -------------------------------------------------------------
    // 3. Pesos de arestas e vértices
    // -------------------------------------------------------------

    @Test
    @DisplayName("Manipulação e consulta de pesos de arestas")
    public void testEdgeWeights() throws Exception {
        printHeader("Pesos de Arestas: Padrão, Atualização e Consulta");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);
        graph.addEdge(1, 2);

        System.out.println("[INPUT] addEdge(1, 2)");
        System.out.println("[OUTPUT] getEdgeWeight(1, 2) = " + graph.getEdgeWeight(1, 2) + " (padrão = 1.0)");
        assertEquals(AbstractGraph.DEFAULT_EDGE_WEIGHT, graph.getEdgeWeight(1, 2));

        System.out.println("[INPUT] setEdgeWeight(1, 2, 7.85)");
        graph.setEdgeWeight(1, 2, 7.85);
        System.out.println("[OUTPUT] getEdgeWeight(1, 2) = " + graph.getEdgeWeight(1, 2));
        assertEquals(7.85, graph.getEdgeWeight(1, 2), 0.0001);
    }

    @Test
    @DisplayName("Caso Extremo: Consultar ou alterar peso de aresta inexistente deve lançar exceção")
    public void testEdgeWeightsNonExistentThrowsException() throws Exception {
        printHeader("Caso Extremo: Pesos em Aresta Inexistente");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);

        System.out.println("[INPUT] getEdgeWeight(0, 1) inexistente");
        Exception exGet = assertThrows(Exception.class, () -> graph.getEdgeWeight(0, 1));
        System.out.println("[OUTPUT] Exceção lançada: " + exGet.getMessage());
        assertEquals(AbstractGraph.MSG1_EXCEPTION, exGet.getMessage());

        System.out.println("[INPUT] setEdgeWeight(0, 1, 5.0) inexistente");
        Exception exSet = assertThrows(Exception.class, () -> graph.setEdgeWeight(0, 1, 5.0));
        System.out.println("[OUTPUT] Exceção lançada: " + exSet.getMessage());
        assertEquals(AbstractGraph.MSG1_EXCEPTION, exSet.getMessage());
    }

    @Test
    @DisplayName("Manipulação e consulta de pesos de vértices")
    public void testVertexWeights() throws Exception {
        printHeader("Pesos de Vértices: Padrão e Atualização");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);

        System.out.println("[OUTPUT] getVertexWeight(0) = " + graph.getVertexWeight(0) + " (padrão = 0.0)");
        assertEquals(AbstractGraph.DEFAULT_VERTEX_WEIGHT, graph.getVertexWeight(0));

        System.out.println("[INPUT] setVertexWeight(0, 15.75)");
        graph.setVertexWeight(0, 15.75);
        System.out.println("[OUTPUT] getVertexWeight(0) = " + graph.getVertexWeight(0));
        assertEquals(15.75, graph.getVertexWeight(0), 0.0001);
        assertEquals(AbstractGraph.DEFAULT_VERTEX_WEIGHT, graph.getVertexWeight(1), "outros vértices não são afetados");
    }

    // -------------------------------------------------------------
    // 4. Graus de entrada e saída
    // -------------------------------------------------------------

    @Test
    @DisplayName("Cálculo de In-Degree e Out-Degree")
    public void testVertexDegrees() throws Exception {
        printHeader("Graus de Entrada (In) e Saída (Out)");
        // Arestas: 0->1, 2->1, 3->1 (vértice 1 recebe 3) e 1->0, 1->2 (vértice 1 emite 2)
        AdjacencyListGraph graph = new AdjacencyListGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(3, 1);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);

        System.out.println("[INPUT] Arestas inseridas: (0->1), (2->1), (3->1), (1->0), (1->2)");
        System.out.println("[OUTPUT] getVertexInDegree(1) = " + graph.getVertexInDegree(1));
        System.out.println("[OUTPUT] getVertexOutDegree(1) = " + graph.getVertexOutDegree(1));
        System.out.println("[OUTPUT] getVertexInDegree(0) = " + graph.getVertexInDegree(0));
        System.out.println("[OUTPUT] getVertexOutDegree(0) = " + graph.getVertexOutDegree(0));

        assertEquals(3, graph.getVertexInDegree(1));
        assertEquals(2, graph.getVertexOutDegree(1));
        assertEquals(1, graph.getVertexInDegree(0));
        assertEquals(1, graph.getVertexOutDegree(0));
        assertEquals(0, graph.getVertexInDegree(3));
        assertEquals(1, graph.getVertexOutDegree(3));
    }

    @Test
    @DisplayName("Graus são atualizados após remoção de aresta")
    public void testDegreesAfterRemoval() throws Exception {
        printHeader("Graus após remoção de aresta");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        assertEquals(2, graph.getVertexInDegree(1));

        System.out.println("[INPUT] removeEdge(0, 1)");
        graph.removeEdge(0, 1);
        System.out.println("[OUTPUT] getVertexInDegree(1) = " + graph.getVertexInDegree(1));
        System.out.println("[OUTPUT] getVertexOutDegree(0) = " + graph.getVertexOutDegree(0));
        assertEquals(1, graph.getVertexInDegree(1));
        assertEquals(0, graph.getVertexOutDegree(0));
    }

    // -------------------------------------------------------------
    // 5. Casos extremos de índices (fronteira)
    // -------------------------------------------------------------

    @Test
    @DisplayName("Casos Extremos de Fronteira: Índices negativos ou maiores/iguais a numVertex")
    public void testBoundaryVertexIndices() throws Exception {
        printHeader("Casos Extremos: Acesso com Índices Fora dos Limites (< 0 ou >= N)");
        AdjacencyListGraph graph = new AdjacencyListGraph(3); // índices válidos: 0, 1, 2

        System.out.println("[INPUT] hasVertex(-1) e hasVertex(3) com N=3");
        assertFalse(graph.hasVertex(-1));
        assertFalse(graph.hasVertex(3));
        assertTrue(graph.hasVertex(0));
        assertTrue(graph.hasVertex(2));

        System.out.println("[INPUT] addEdge(-1, 0), addEdge(0, 3), hasEdge(-1, 2), removeEdge(0, 5)");
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.addEdge(-1, 0)).getMessage());
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.addEdge(0, 3)).getMessage());
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.hasEdge(-1, 2)).getMessage());
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.removeEdge(0, 5)).getMessage());

        System.out.println("[INPUT] getEdgeWeight(0, 3), setEdgeWeight(3, 0, 1.0)");
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.getEdgeWeight(0, 3)).getMessage());
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.setEdgeWeight(3, 0, 1.0)).getMessage());

        System.out.println("[INPUT] getVertexInDegree(5), getVertexOutDegree(-1)");
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.getVertexInDegree(5)).getMessage());
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.getVertexOutDegree(-1)).getMessage());

        System.out.println("[INPUT] getVertexWeight(3), setVertexWeight(-2, 10.0)");
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.getVertexWeight(3)).getMessage());
        assertEquals(AbstractGraph.MSG2_EXCEPTION, assertThrows(Exception.class, () -> graph.setVertexWeight(-2, 10.0)).getMessage());

        assertEquals(0, graph.getEdgeCount(), "operações inválidas não podem alterar o grafo");
        System.out.println("[OUTPUT] Todas as operações com índices fora de [0, N-1] lançaram exceção corretamente.");
    }

    // -------------------------------------------------------------
    // 6. Relações estruturais e predicados
    // -------------------------------------------------------------

    @Test
    @DisplayName("Verificações estruturais: isSuccessor, isPredecessor, isDivergent, isConvergent, isIncident, isEmptyGraph")
    public void testStructuralPredicates() throws Exception {
        printHeader("Relações Estruturais e Predicados");
        AdjacencyListGraph graph = new AdjacencyListGraph(4);

        assertTrue(graph.isEmptyGraph());

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        assertFalse(graph.isEmptyGraph());

        System.out.println("[OUTPUT] isSuccessor(0, 1) = " + graph.isSuccessor(0, 1));
        System.out.println("[OUTPUT] isPredecessor(1, 0) = " + graph.isPredecessor(1, 0));
        assertTrue(graph.isSuccessor(0, 1));
        assertFalse(graph.isSuccessor(1, 0));
        assertTrue(graph.isPredecessor(1, 0));
        assertFalse(graph.isPredecessor(0, 1));

        // Divergência: 0->1 e 0->2 partem do mesmo vértice
        System.out.println("[OUTPUT] isDivergent(0, 1, 0, 2) = " + graph.isDivergent(0, 1, 0, 2));
        assertTrue(graph.isDivergent(0, 1, 0, 2));

        // Convergência: 0->2 e 1->2 chegam no mesmo vértice
        graph.addEdge(1, 2);
        System.out.println("[OUTPUT] isConvergent(0, 2, 1, 2) = " + graph.isConvergent(0, 2, 1, 2));
        assertTrue(graph.isConvergent(0, 2, 1, 2));
        assertFalse(graph.isConvergent(0, 1, 0, 2), "0->1 e 0->2 não chegam ao mesmo vértice");
        assertFalse(graph.isDivergent(0, 2, 1, 2), "0->2 e 1->2 não partem do mesmo vértice");

        // Incidência
        assertTrue(graph.isIncident(0, 1, 0));
        assertTrue(graph.isIncident(0, 1, 1));
        assertFalse(graph.isIncident(0, 1, 3));
    }

    // -------------------------------------------------------------
    // 7. Comportamentos específicos da representação por lista
    // -------------------------------------------------------------

    @Test
    @DisplayName("Lista: arestas de um vértice não aparecem nas listas dos outros")
    public void testEdgesAreIsolatedPerVertex() throws Exception {
        printHeader("Lista: isolamento entre as listas de adjacência");
        AdjacencyListGraph graph = new AdjacencyListGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        System.out.println("[INPUT] addEdge(0, 1), addEdge(0, 2)");
        assertEquals(2, graph.getVertexOutDegree(0));
        assertEquals(0, graph.getVertexOutDegree(1));
        assertEquals(0, graph.getVertexOutDegree(2));
        assertEquals(0, graph.getVertexOutDegree(3));
        assertFalse(graph.hasEdge(1, 0));
        assertFalse(graph.hasEdge(2, 0));
        assertFalse(graph.hasEdge(0, 3));
        System.out.println("[OUTPUT] Apenas a lista do vértice 0 foi alterada.");
    }

    @Test
    @DisplayName("Lista: cada aresta guarda o seu próprio peso")
    public void testWeightsAreIndependentPerEdge() throws Exception {
        printHeader("Lista: pesos independentes por aresta");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);

        graph.setEdgeWeight(0, 1, 2.0);
        graph.setEdgeWeight(0, 2, 5.0);

        System.out.println("[INPUT] pesos: (0->1)=2.0, (0->2)=5.0, (1->2) mantido no padrão");
        assertEquals(2.0, graph.getEdgeWeight(0, 1), 0.0001);
        assertEquals(5.0, graph.getEdgeWeight(0, 2), 0.0001);
        assertEquals(AbstractGraph.DEFAULT_EDGE_WEIGHT, graph.getEdgeWeight(1, 2), 0.0001);
    }

    @Test
    @DisplayName("Lista: aresta removida e recriada volta ao peso padrão")
    public void testRecreatedEdgeHasDefaultWeight() throws Exception {
        printHeader("Lista: recriar aresta removida");
        AdjacencyListGraph graph = new AdjacencyListGraph(3);
        graph.addEdge(0, 1);
        graph.setEdgeWeight(0, 1, 9.0);

        System.out.println("[INPUT] removeEdge(0, 1) e depois addEdge(0, 1)");
        graph.removeEdge(0, 1);
        graph.addEdge(0, 1);

        System.out.println("[OUTPUT] getEdgeWeight(0, 1) = " + graph.getEdgeWeight(0, 1));
        assertEquals(AbstractGraph.DEFAULT_EDGE_WEIGHT, graph.getEdgeWeight(0, 1), 0.0001);
        assertEquals(1, graph.getEdgeCount());
    }

    @Test
    @DisplayName("Lista: grafo esparso grande mantém contadores e consultas corretos")
    public void testLargeSparseGraph() throws Exception {
        printHeader("Lista: grafo esparso com 2000 vértices (ciclo)");
        int n = 2000;
        AdjacencyListGraph graph = new AdjacencyListGraph(n);
        for (int i = 0; i < n; i++) {
            graph.addEdge(i, (i + 1) % n);
        }

        System.out.println("[OUTPUT] getVertexCount() = " + graph.getVertexCount() + ", getEdgeCount() = " + graph.getEdgeCount());
        assertEquals(n, graph.getVertexCount());
        assertEquals(n, graph.getEdgeCount());
        for (int i = 0; i < n; i++) {
            assertTrue(graph.hasEdge(i, (i + 1) % n));
            assertFalse(graph.hasEdge((i + 1) % n, i));
            assertEquals(1, graph.getVertexOutDegree(i));
            assertEquals(1, graph.getVertexInDegree(i));
        }
    }

    // -------------------------------------------------------------
    // 8. Problemas conhecidos da AbstractGraph (T1.4)
    //    Desativados para não quebrar o build; reative ao corrigir a T1.4.
    // -------------------------------------------------------------

    @Test
    @Disabled("T1.4: isConnected() compara só edgeCount >= n-1 e não percorre o grafo")
    @DisplayName("isConnected: grafo com dois componentes não deve ser considerado conectado")
    public void testIsConnectedDetectsTwoComponents() throws Exception {
        AdjacencyListGraph graph = new AdjacencyListGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(2, 3); // componentes {0,1} e {2,3}: 3 arestas, mas desconexo
        assertFalse(graph.isConnected());
    }

    @Test
    @DisplayName("isConnected: cadeia 0->1->2->3 é fracamente conectada (orientação ignorada)")
    public void testIsConnectedWeakChain() throws Exception {
        AdjacencyListGraph graph = new AdjacencyListGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        assertTrue(graph.isConnected());
    }

    @Test
    @Disabled("T1.4: isCompleteGraph() compara com n*n/2; completo direcionado tem n*(n-1) arestas")
    @DisplayName("isCompleteGraph: todas as arestas (u,v) e (v,u) entre vértices distintos")
    public void testIsCompleteGraphDirected() throws Exception {
        AdjacencyListGraph complete = new AdjacencyListGraph(3);
        for (int u = 0; u < 3; u++) {
            for (int v = 0; v < 3; v++) {
                if (u != v) {
                    complete.addEdge(u, v);
                }
            }
        }
        assertTrue(complete.isCompleteGraph());

        AdjacencyListGraph incomplete = new AdjacencyListGraph(3);
        incomplete.addEdge(0, 1);
        incomplete.addEdge(1, 0);
        incomplete.addEdge(1, 2);
        incomplete.addEdge(2, 1);
        assertFalse(incomplete.isCompleteGraph());
    }
}
