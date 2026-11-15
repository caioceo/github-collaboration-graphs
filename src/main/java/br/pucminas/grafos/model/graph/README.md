# Camada de Modelo (model/)

Contém o núcleo da lógica de negócio, estruturas de dados de grafos, entidades de domínio e os algoritmos de análise.

##  Subpacotes

### `model/graph/` (Biblioteca de Grafos - Etapa 1)
Implementação proprietária para grafos simples, direcionados e ponderados com vértices inteiros de `0` a `numVertices - 1`:
* `AbstractGraph.java`: Classe abstrata que centraliza a API obrigatória, validações de limites e atributos compartilhados.
* `AdjacencyMatrixGraph.java`: Implementação concreta baseada em matriz de adjacência bidimensional.
* `AdjacencyListGraph.java`: Implementação concreta baseada em listas de adjacência / mapas encadeados.

### `model/github/` (Domínio do Repositório OpenClaude)
Entidades para representação dos dados coletados:
* `User.java`: Representação do colaborador e seu identificador na rede.
* `Interaction.java`: Representação do relacionamento direcionado entre usuários.
* `InteractionType.java`: Enumeração dos tipos de interação com os respectivos pesos (Comentário = 2, Fechamento de Issue = 3, Revisão de PR = 4, Merge de PR = 5).

### `model/algorithms/` (Algoritmos da Literatura - Etapa 2)
Implementações manuais de dois métodos avançados operando exclusivamente sobre o `AbstractGraph`:
* Algoritmo 1 (ex.: PageRank / Intermediação / Proximidade).
* Algoritmo 2 (ex.: Detecção de Comunidades via Girvan-Newman / Propagação de Rótulos).