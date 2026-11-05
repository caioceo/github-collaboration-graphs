# Camada de Visualização (view/)

Responsável pela interação direta com o usuário e pela geração das saídas de arquivos de visualização externa.

## Componentes

* `ConsoleView.java`:
  * Interface em linha de comando (CLI) que disponibiliza menus interativos no terminal.
  * Exibe resumos quantitativos (contagem de vértices e arestas, graus de entrada/saída, densidade).
  * Apresenta os rankings e métricas retornados pelos algoritmos de análise.
* `GephiExporterView.java`:
  * Lida com a formatação e serialização das instâncias de `AbstractGraph` para arquivos compatíveis com o Gephi (GEXF, GraphML ou CSV).
  * Grava os arquivos finais no diretório `data/gephi/`.