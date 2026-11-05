# Camada Controladora (controller/)

Responsável por intermediar as ações da interface com o usuário (`view`) e as regras de negócio (`model`).

##  Controladores

* `GitHubCollectorController.java`:
  * Realiza requisições à API do GitHub (REST/GraphQL) para o repositório OpenClaude.
  * Gerencia paginação de resultados, limites de requisições (*rate limits*), erros de conexão e persistência de dados brutos na pasta `data/raw/`.
* `GraphBuilderController.java`:
  * Mapeia os nomes/logins de usuários para identificadores inteiros contínuos (`0` a `numVertices - 1`).
  * Constrói e preenche os 3 grafos temáticos e o grafo consolidado, acumulando pesos em chamadas repetidas.
* `AnalysisController.java`:
  * Coordena a chamada dos algoritmos de análise sobre o grafo consolidado e os grafos específicos.
  * Repassa os resultados para formatação na visualização e exportação para Gephi.