# Testes Automatizados (src/test/)

Suíte de testes unitários desenvolvida em JUnit para validação de integridade e requisitos técnicos.

##  Escopo dos Testes

1. **Biblioteca de Grafos (`model/graph/`):**
   - Criação e integridade de limites de vértices.
   - Inserção idempotente e remoção de arestas.
   - Bloqueio de laços (arestas de um vértice para ele mesmo).
   - Verificação de sucessor, predecessor, divergência, convergência e incidência.
   - Conectividade fraca (`isConnected()`), grafo vazio (`isEmptyGraph()`) e grafo completo (`isCompleteGraph()`).
   - Lançamento de exceções em acessos a índices inválidos e consultas de peso em arestas inexistentes.
   - **Equivalência Estrutural:** Testes parametrizados para assegurar que `AdjacencyMatrixGraph` e `AdjacencyListGraph` produzam saídas estritamente idênticas para os mesmos cenários de entrada.

2. **Algoritmos (`model/algorithms/`):**
   - Execução dos algoritmos em grafos didáticos/conhecidos para validação dos resultados calculados.

3. **Coleta e Parsing (`controller/`):**
   - Testes unitários com arquivos JSON mockados para validar a correta extração das interações do OpenClaude.