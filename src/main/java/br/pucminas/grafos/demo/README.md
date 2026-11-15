# Programa de Demonstração da Biblioteca (demo/)

Entregável da Etapa 1. O enunciado pede uma **aplicação separada** que demonstre as duas representações e todas as operações da API, por isso ela tem o seu próprio `main` e não faz parte da CLI da Etapa 2.

## Componentes

* `GraphLibraryDemo.java` (T2.2): roda o mesmo roteiro em `AdjacencyMatrixGraph` e em `AdjacencyListGraph`:
  * criação do grafo, inserção (incluindo repetida) e remoção de arestas;
  * tentativa de laço e de índice inválido, mostrando as exceções;
  * sucessor, predecessor, divergência, convergência e incidência;
  * graus de entrada e saída, pesos de vértices e arestas;
  * conectividade fraca, grafo vazio e grafo completo;
  * exportação para o Gephi em `data/gephi/`.

Este pacote só depende de `model/graph`.

## Execução

```bash
./mvnw compile exec:java -Dexec.mainClass=br.pucminas.grafos.demo.GraphLibraryDemo
```
