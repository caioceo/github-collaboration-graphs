# Análise de Redes de Colaboração do Repositório OpenClaude

Projeto prático da disciplina de Teoria de Grafos e Computabilidade da Pontifícia Universidade Católica de Minas Gerais (PUC Minas), 2026/2. O enunciado completo está em [`docs/enunciado.pdf`](docs/enunciado.pdf).

---

## Visão Geral do Projeto

O trabalho é uma ferramenta orientada a objetos dividida em duas etapas integradas:
1. **Etapa 1:** biblioteca em Java para grafos simples, direcionados e ponderados, com uma API comum implementada por matriz e por lista de adjacência.
2. **Etapa 2:** coleta de dados pela API do GitHub, modelagem das interações da comunidade do repositório **OpenClaude**, construção dos grafos com a biblioteca da Etapa 1 e aplicação de dois algoritmos da literatura.

---

## Como executar

Pré-requisito: JDK 21. O Maven não precisa estar instalado, o projeto usa o Maven Wrapper.

```bash
# compilar e rodar os testes
./mvnw test            # Linux/macOS
mvnw.cmd test          # Windows

# executar a aplicação principal
./mvnw compile exec:java
```

Para a coleta (Etapa 2), copie `.env.example` para `.env` e preencha `GITHUB_TOKEN`. O `.env` está no `.gitignore` e nunca deve ser commitado.

---

## Estrutura do repositório

```
.
├── pom.xml                      # build Maven (JUnit 5; sem bibliotecas de grafos)
├── mvnw / mvnw.cmd / .mvn/      # Maven Wrapper
├── .env.example                 # modelo do token do GitHub
├── data/
│   ├── raw/                     # respostas brutas da API do GitHub (reprodução sem nova coleta)
│   └── gephi/                   # grafos exportados (.gexf)
├── docs/
│   ├── enunciado.pdf            # enunciado do trabalho
│   └── artigo.tex               # relatório no modelo SBC
└── src/
    ├── main/java/br/pucminas/grafos/
    │   ├── Main.java            # CLI da Etapa 2
    │   ├── demo/                # programa de demonstração da biblioteca (Etapa 1)
    │   ├── model/
    │   │   ├── graph/           # biblioteca: AbstractGraph, AdjacencyMatrixGraph, AdjacencyListGraph
    │   │   ├── github/          # entidades: User, Interaction, InteractionType
    │   │   └── algorithms/      # os dois algoritmos da literatura
    │   ├── controller/          # coleta no GitHub, construção dos grafos, orquestração das análises
    │   └── view/                # saída no terminal
    └── test/
        ├── java/br/pucminas/grafos/   # mesma árvore de pacotes do main
        └── resources/github/          # JSONs de exemplo para testar o parsing
```

Regra de dependência: `model/graph` não importa nada dos outros pacotes. É a biblioteca da Etapa 1 e precisa funcionar sozinha. O restante do projeto usa a biblioteca, nunca o contrário.

---

## Equipe e Responsabilidades

* **Membro 1**: abstrações base, exportação para Gephi e Algoritmo 1 da literatura.
* **Membro 2**: representação em matriz, programa de demonstração e Algoritmo 2 da literatura.
* **Membro 3**: representação em lista, persistência local dos dados e exportações Gephi.
* **Membro 4**: verificações estruturais dos grafos, mapeamento de índices inteiros e CLI final.
* **Membro 5**: testes unitários, construção dos grafos ponderados e redação do relatório SBC.

---

## Regras de Versionamento e Fluxo Git

O histórico precisa deixar claras as contribuições individuais, porque ele pode ser usado na avaliação.

### 1. Nomenclatura de Branches
* `main`: só código estável, testado e pronto para entrega. Commits diretos são proibidos.
* `dev`: branch de integração das funcionalidades.
* Branches de trabalho saem da `dev`:
  * `feat/<nome-da-funcionalidade>`: novas classes, métodos ou scripts.
  * `fix/<descricao-do-bug>`: correção de defeitos.
  * `test/<componente>`: criação ou expansão de testes JUnit.
  * `docs/<secao>`: documentação ou artigo em LaTeX.

### 2. Padrão de Commits (Conventional Commits)
* `feat:` nova funcionalidade na biblioteca, análise ou CLI.
* `fix:` correção de bugs.
* `test:` testes unitários.
* `docs:` documentação ou relatório LaTeX.
* `refactor:` mudança de código sem alterar o comportamento externo.
* `build:` configuração de build e dependências.

### 3. Regras de Pull Request (PR) e Revisão de Código
* **Destino:** todo PR aponta para a `dev`. Só a `dev` abre PR para a `main`, na entrega de cada etapa.
* **Identificação:** título e descrição citam a tarefa (exemplo: "Resolve T1.2").
* **Aprovação:** pelo menos 1 colega do grupo revisa e aprova antes do merge.
* **Compilação e testes:** nenhum PR entra se a compilação falhar ou se algum teste falhar.
* **Restrição acadêmica:** é proibido usar bibliotecas prontas de grafos (JGraphT, NetworkX e similares). O revisor deve rejeitar PRs que descumpram essa regra.

---

## Planejamento em Sprints e Distribuição de Tarefas

O desenvolvimento está dividido em 4 sprints, com dificuldade Fácil, Média, Difícil ou Muito Difícil.

### Sprint 1: Biblioteca de Grafos Base (Etapa 1 - Parte A)
**Meta:** API base, restrições e as duas representações em memória.
* [ ] **T1.1 (Model):** classe abstrata `AbstractGraph` com validação de índices, pesos padrão e métodos abstratos.
  * Dificuldade: Média
  * Responsável: Membro 1
* [ ] **T1.2 (Model):** classe `AdjacencyMatrixGraph` com arestas direcionadas, laços proibidos e pesos.
  * Dificuldade: Média
  * Responsável: Membro 2
* [ ] **T1.3 (Model):** classe `AdjacencyListGraph` com listas/mapas, pesos e idempotência.
  * Dificuldade: Média
  * Responsável: Membro 3
* [ ] **T1.4 (Model):** `isConnected()` (conectividade fraca), `isEmptyGraph()`, `isCompleteGraph()`, convergência, divergência e incidência.
  * Dificuldade: Difícil
  * Responsável: Membro 4
* [ ] **T1.5 (Test):** testes JUnit de criação, laços inválidos, exceções e equivalência de comportamento entre matriz e lista.
  * Dificuldade: Difícil
  * Responsável: Membro 5

---

### Sprint 2: Exportação Gephi e Demonstração da Biblioteca (Etapa 1 - Parte B)
**Meta:** fechar os entregáveis da Etapa 1.
* [ ] **T2.1 (Model):** `exportToGEPHI(String path)` gerando GEXF, GraphML ou CSV compatível com o Gephi, preservando pesos e direções.
  * Dificuldade: Média
  * Responsável: Membro 1
* [ ] **T2.2 (Demo):** programa de demonstração separado (pacote `demo/`) executando todas as operações da API nas duas representações.
  * Dificuldade: Média
  * Responsável: Membro 2
* [ ] **T2.3 (Docs):** documentação das decisões de implementação e instruções de execução.
  * Dificuldade: Fácil
  * Responsável: Membro 3
* [ ] **T2.4 (Review):** auditoria do código, conferência das regras do trabalho e entrega da Etapa 1.
  * Dificuldade: Média
  * Responsável: Membros 4 e 5

---

### Sprint 3: Coleta e Modelagem da Rede OpenClaude (Etapa 2 - Parte A)
**Meta:** coletar os dados pela API e construir os grafos com as interações da comunidade.
* [ ] **T3.1 (Controller):** cliente HTTP para a API do GitHub (token via `.env`, paginação, limite de requisições e erros).
  * Dificuldade: Difícil
  * Responsável: Membro 1
* [ ] **T3.2 (Model):** entidades `User`, `Interaction` e `InteractionType` (comentário = 2, fechamento = 3, revisão/aprovação = 4, merge = 5) e filtros de bots, contas excluídas e auto-interações.
  * Dificuldade: Fácil
  * Responsável: Membro 2
* [ ] **T3.3 (Data):** salvar as respostas brutas em `data/raw/` para reproduzir a análise sem nova coleta completa.
  * Dificuldade: Média
  * Responsável: Membro 3
* [ ] **T3.4 (Controller):** indexador biunívoco que converte logins em inteiros contínuos de `0` até `numVertices - 1`.
  * Dificuldade: Média
  * Responsável: Membro 4
* [ ] **T3.5 (Controller):** construir os três grafos (discussões, gerenciamento de issues, revisão/integração) e o grafo consolidado, acumulando pesos sem contagem dupla.
  * Dificuldade: Difícil
  * Responsável: Membro 5

---

### Sprint 4: Algoritmos, Análise e Relatório (Etapa 2 - Parte B)
**Meta:** aplicar os algoritmos, analisar os resultados e entregar o relatório.
* [ ] **T4.1 (Model):** Algoritmo 1 da literatura, implementado sobre `AbstractGraph`, com testes em grafos de resultado conhecido.
  * Dificuldade: Muito Difícil
  * Responsável: Membro 1
* [ ] **T4.2 (Model):** Algoritmo 2 da literatura, implementado sobre `AbstractGraph`, com testes em grafos de resultado conhecido.
  * Dificuldade: Muito Difícil
  * Responsável: Membro 2
* [ ] **T4.3 (Data):** exportar os quatro grafos para `data/gephi/` e gerar as visualizações no Gephi.
  * Dificuldade: Média
  * Responsável: Membro 3
* [ ] **T4.4 (View/Controller):** CLI final: carregar dados, construir grafos, mostrar vértices e arestas, rodar os dois algoritmos, consultar resultados e exportar.
  * Dificuldade: Média
  * Responsável: Membro 4
* [ ] **T4.5 (Docs):** relatório SBC (7 a 15 páginas) com análise, limitações e contribuições individuais.
  * Dificuldade: Difícil
  * Responsável: Membro 5
