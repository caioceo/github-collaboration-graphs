# Camada de Visualização (view/)

Interação com o usuário no terminal.

## Componentes

* `ConsoleView.java`:
  * Menus da CLI da Etapa 2.
  * Mostra resumos dos grafos (vértices, arestas, graus de entrada/saída).
  * Mostra os rankings e métricas devolvidos pelos algoritmos.

## Exportação para o Gephi

A exportação **não** fica nesta camada. O enunciado exige `exportToGEPHI(String path)` como parte da API da biblioteca, então ela está implementada em `model/graph/AbstractGraph`. A view (ou o controller) só escolhe o caminho em `data/gephi/` e chama `grafo.exportToGEPHI(caminho)`.
