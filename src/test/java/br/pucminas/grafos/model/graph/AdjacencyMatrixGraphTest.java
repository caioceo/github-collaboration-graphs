package br.pucminas.grafos.model.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyMatrixGraphTest {

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

        System.out.println("[INPUT] new AdjacencyMatrixGraph(1)");
        AdjacencyMatrixGraph g1 = new AdjacencyMatrixGraph(1);
        System.out.println("[OUTPUT] getVertexCount() = " + g1.getVertexCount() + ", getEdgeCount() = " + g1.getEdgeCount());
        assertEquals(1, g1.getVertexCount());
        assertEquals(0, g1.getEdgeCount());

        System.out.println("[INPUT] new AdjacencyMatrixGraph(4)");
        AdjacencyMatrixGraph g4 = new AdjacencyMatrixGraph(4);
        System.out.println("[OUTPUT] getVertexCount() = " + g4.getVertexCount() + ", getEdgeCount() = " + g4.getEdgeCount());
        assertEquals(4, g4.getVertexCount());
        assertEquals(0, g4.getEdgeCount());
    }

    @Test
    @DisplayName("Caso Extremo: Criação com 0 ou vértices negativos deve lançar exceção")
    public void testCreateGraphZeroOrNegative() {
        printHeader("Caso Extremo: Criação com 0 e vértices negativos");

        System.out.println("[INPUT] new AdjacencyMatrixGraph(0)");
        Exception ex0 = assertThrows(Exception.class, () -> new AdjacencyMatrixGraph(0));
        System.out.println("[OUTPUT] Exceção lançada: " + ex0.getMessage());
        assertEquals(AbstractGraph.MSG5_EXCEPTION, ex0.getMessage());

        System.out.println("[INPUT] new AdjacencyMatrixGraph(-1)");
        Exception exNeg = assertThrows(Exception.class, () -> new AdjacencyMatrixGraph(-1));
        System.out.println("[OUTPUT] Exceção lançada: " + exNeg.getMessage());
        assertEquals(AbstractGraph.MSG5_EXCEPTION, exNeg.getMessage());

        System.out.println("[INPUT] new AdjacencyMatrixGraph(-50)");
        Exception exNegLarge = assertThrows(Exception.class, () -> new AdjacencyMatrixGraph(-50));
        System.out.println("[OUTPUT] Exceção lançada: " + exNegLarge.getMessage());
        assertEquals(AbstractGraph.MSG5_EXCEPTION, exNegLarge.getMessage());
    }

    // -------------------------------------------------------------
    // 2. Inserção, consulta e remoção de arestas
    // -------------------------------------------------------------

    @Test
    @DisplayName("Adicionar e verificar arestas direcionadas")
    public void testAddAndHasEdge() throws Exception {
        printHeader("Adição e Verificação de Aresta Direcionada (0 -> 1)");
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

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
    @DisplayName("Caso Extremo: Adicionar aresta duplicada deve lançar exceção")
    public void testAddDuplicateEdgeThrowsException() throws Exception {
        printHeader("Caso Extremo: Adição de Aresta Duplicada");
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

        System.out.println("[INPUT] 1ª chamada: addEdge(0, 1)");
        graph.addEdge(0, 1);

        System.out.println("[INPUT] 2ª chamada: addEdge(0, 1) (duplicada)");
        Exception ex = assertThrows(Exception.class, () -> graph.addEdge(0, 1));
        System.out.println("[OUTPUT] Exceção lançada: " + ex.getMessage());
        assertEquals(AbstractGraph.MSG3_EXCEPTION, ex.getMessage());
        assertEquals(1, graph.getEdgeCount());
    }

    @Test
    @DisplayName("Caso Extremo: Adicionar laço (self-loop u -> u) deve lançar exceção")
    public void testSelfLoopThrowsException() throws Exception {
        printHeader("Caso Extremo: Bloqueio de Laços (u -> u)");
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

        System.out.println("[INPUT] addEdge(1, 1)");
        Exception ex = assertThrows(Exception.class, () -> graph.addEdge(1, 1));
        System.out.println("[OUTPUT] Exceção lançada: " + ex.getMessage());
        assertEquals(AbstractGraph.MSG6_EXCEPTION, ex.getMessage());
        assertEquals(0, graph.getEdgeCount());
    }

    @Test
    @DisplayName("Remover aresta existente e consultar após remoção")
    public void testRemoveEdge() throws Exception {
        printHeader("Remoção de Aresta Existente");
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
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
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

        System.out.println("[INPUT] removeEdge(0, 1) em grafo vazio");
        Exception ex = assertThrows(Exception.class, () -> graph.removeEdge(0, 1));
        System.out.println("[OUTPUT] Exceção lançada: " + ex.getMessage());
        assertEquals(AbstractGraph.MSG4_EXCEPTION, ex.getMessage());
    }

    // -------------------------------------------------------------
    // 3. Pesos de arestas e vértices
    // -------------------------------------------------------------

    @Test
    @DisplayName("Manipulação e consulta de pesos de arestas")
    public void testEdgeWeights() throws Exception {
        printHeader("Pesos de Arestas: Padrão, Atualização e Consulta");
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
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
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

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
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

        System.out.println("[OUTPUT] getVertexWeight(0) = " + graph.getVertexWeight(0) + " (padrão = 0.0)");
        assertEquals(AbstractGraph.DEFAULT_VERTEX_WEIGHT, graph.getVertexWeight(0));

        System.out.println("[INPUT] setVertexWeight(0, 15.75)");
        graph.setVertexWeight(0, 15.75);
        System.out.println("[OUTPUT] getVertexWeight(0) = " + graph.getVertexWeight(0));
        assertEquals(15.75, graph.getVertexWeight(0), 0.0001);
    }

    // -------------------------------------------------------------
    // 4. Graus de entrada e saída (in-degree e out-degree)
    // -------------------------------------------------------------

    @Test
    @DisplayName("Cálculo de In-Degree e Out-Degree")
    public void testVertexDegrees() throws Exception {
        printHeader("Graus de Entrada (In) e Saída (Out)");
        // Grafo com 4 vértices
        // Arestas: 0->1, 2->1, 3->1 (vértice 1 recebe 3)
        // Arestas: 1->0, 1->2 (vértice 1 emite 2)
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4);
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
    }

    // -------------------------------------------------------------
    // 5. Casos Extremos de Limites de Índices (Fronteira)
    // -------------------------------------------------------------

    @Test
    @DisplayName("Casos Extremos de Fronteira: Índices negativos ou maiores/iguais a numVertex")
    public void testBoundaryVertexIndices() throws Exception {
        printHeader("Casos Extremos: Acesso com Índices Fora dos Limites (< 0 ou >= N)");
        int n = 3; // índices válidos: 0, 1, 2
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n);

        System.out.println("[INPUT] hasVertex(-1)");
        assertFalse(graph.hasVertex(-1));
        System.out.println("[INPUT] hasVertex(3) com N=3");
        assertFalse(graph.hasVertex(3));

        System.out.println("[INPUT] addEdge(-1, 0)");
        assertThrows(Exception.class, () -> graph.addEdge(-1, 0));

        System.out.println("[INPUT] addEdge(0, 3) com N=3");
        assertThrows(Exception.class, () -> graph.addEdge(0, 3));

        System.out.println("[INPUT] hasEdge(-1, 2)");
        assertThrows(Exception.class, () -> graph.hasEdge(-1, 2));

        System.out.println("[INPUT] getVertexInDegree(5)");
        assertThrows(Exception.class, () -> graph.getVertexInDegree(5));

        System.out.println("[INPUT] getVertexOutDegree(-1)");
        assertThrows(Exception.class, () -> graph.getVertexOutDegree(-1));

        System.out.println("[INPUT] getVertexWeight(3)");
        assertThrows(Exception.class, () -> graph.getVertexWeight(3));

        System.out.println("[INPUT] setVertexWeight(-2, 10.0)");
        assertThrows(Exception.class, () -> graph.setVertexWeight(-2, 10.0));

        System.out.println("[OUTPUT] Todas as operações com índices fora de [0, N-1] lançaram exceção corretamente.");
    }

    // -------------------------------------------------------------
    // 6. Relações Estruturais e Predicados
    // -------------------------------------------------------------

    @Test
    @DisplayName("Verificações estruturais: isSuccessor, isPredecessor, isDivergent, isConvergent, isIncident, isEmptyGraph")
    public void testStructuralPredicates() throws Exception {
        printHeader("Relações Estruturais e Predicados");
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4);

        System.out.println("[OUTPUT] isEmptyGraph() inicial = " + graph.isEmptyGraph());
        assertTrue(graph.isEmptyGraph());

        // Inserir arestas: 0->1 e 0->2
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        System.out.println("[OUTPUT] isEmptyGraph() após addEdge = " + graph.isEmptyGraph());
        assertFalse(graph.isEmptyGraph());

        System.out.println("[OUTPUT] isSuccessor(0, 1) = " + graph.isSuccessor(0, 1));
        System.out.println("[OUTPUT] isPredecessor(1, 0) = " + graph.isPredecessor(1, 0));
        assertTrue(graph.isSuccessor(0, 1));
        assertTrue(graph.isPredecessor(1, 0));

        // Divergência: 0->1 e 0->2 partem do mesmo vértice u=0 e chegam em vértices distintos
        System.out.println("[OUTPUT] isDivergent(0, 1, 0, 2) = " + graph.isDivergent(0, 1, 0, 2));
        assertTrue(graph.isDivergent(0, 1, 0, 2));

        // Convergência: 0->2 e 1->2 chegam no mesmo vértice v=2
        graph.addEdge(1, 2);
        System.out.println("[OUTPUT] isConvergent(0, 2, 1, 2) = " + graph.isConvergent(0, 2, 1, 2));
        assertTrue(graph.isConvergent(0, 2, 1, 2));

        // Incidência: vértice 0 é incidente à aresta (0, 1)
        System.out.println("[OUTPUT] isIncident(0, 1, 0) = " + graph.isIncident(0, 1, 0));
        System.out.println("[OUTPUT] isIncident(0, 1, 1) = " + graph.isIncident(0, 1, 1));
        System.out.println("[OUTPUT] isIncident(0, 1, 3) = " + graph.isIncident(0, 1, 3));
        assertTrue(graph.isIncident(0, 1, 0));
        assertTrue(graph.isIncident(0, 1, 1));
        assertFalse(graph.isIncident(0, 1, 3));
    }

    // -------------------------------------------------------------
    // 7. Visualização da Matriz com Inputs e Outputs
    // -------------------------------------------------------------

    @Test
    @DisplayName("Visualização da matriz de adjacência antes e depois das operações")
    public void testViewMatrixVisual() throws Exception {
        printHeader("Visualização da Matriz de Adjacência");
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

        System.out.println("[ESTADO INICIAL] Matriz 3x3 sem arestas:");
        graph.viewMatrix();

        System.out.println("\n[INPUT] Adicionando (0->1), (1->2), (2->0):");
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        System.out.println("[OUTPUT] Matriz após adições:");
        graph.viewMatrix();

        System.out.println("\n[INPUT] Alterando peso de (1->2) para 9.5:");
        graph.setEdgeWeight(1, 2, 9.5);
        System.out.println("[OUTPUT] Matriz após alteração de peso:");
        graph.viewMatrix();

        System.out.println("\n[INPUT] Removendo aresta (0->1):");
        graph.removeEdge(0, 1);
        System.out.println("[OUTPUT] Matriz após remoção:");
        graph.viewMatrix();
    }
}
