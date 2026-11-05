# Análise de Redes de Colaboração do Repositório OpenClaude

Projeto prático desenvolvido para a disciplina de Teoria de Grafos e Computabilidade da Pontifícia Universidade Católica de Minas Gerais (PUC Minas)[cite: 1].

---

## Visão Geral do Projeto

O trabalho compreende o desenvolvimento de uma ferramenta computacional orientada a objetos dividida em duas etapas obrigatórias e integradas[cite: 1]:
1. **Etapa 1:** Construção de uma biblioteca em Java para manipulação de grafos simples, direcionados e ponderados, fornecendo uma API comum implementada por matriz e por lista de adjacência[cite: 1, 2].
2. **Etapa 2:** Coleta de dados via API do GitHub, modelagem das interações da comunidade do repositório de código aberto **OpenClaude**, instanciação de grafos por meio da biblioteca própria desenvolvida na Etapa 1 e aplicação de algoritmos da literatura acadêmica[cite: 1, 3, 4, 5].

---

## Equipe e Responsabilidades

* **Membro 1**: Implementação de abstrações base, exportação para Gephi e Algoritmo 1 da literatura[cite: 1, 2, 5].
* **Membro 2**: Implementação de representação em matriz, CLI de demonstração e Algoritmo 2 da literatura[cite: 2, 3, 5].
* **Membro 3**: Implementação de representação em lista, persistência local de dados e exportações Gephi[cite: 2, 4, 5].
* **Membro 4**: Verificações estruturais de grafos, mapeamento de índices inteiros e CLI final[cite: 2, 3, 4, 5].
* **Membro 5**: Suíte de testes unitários automatizados, construção dos grafos ponderados e redação do relatório SBC[cite: 1, 2, 4, 5].

---

## Regras de Versionamento e Fluxo Git

Para manter o histórico auditável e evidenciar as contribuições individuais exigidas no trabalho[cite: 1, 6]:

### 1. Nomenclatura de Branches
* `main`: Contém unicamente código estável, testado e pronto para entrega das etapas. Commits diretos são proibidos[cite: 1].
* `develop`: Branch de integração contínua entre as funcionalidades.
* Branches de trabalho devem ser criadas a partir da branch `develop`:
  * `feat/<nome-da-funcionalidade>`: Novas classes, métodos ou scripts.
  * `fix/<descricao-do-bug>`: Correção de defeitos em algoritmos ou estruturas.
  * `test/<componente>`: Criação ou expansão de testes unitários com JUnit[cite: 3].
  * `docs/<secao>`: Atualizações de documentação ou escrita do artigo em LaTeX[cite: 1, 5].

### 2. Padrão de Commits (Conventional Commits)
* `feat:` Nova funcionalidade na biblioteca, análise ou CLI.
* `fix:` Correção de bugs.
* `test:` Adição ou modificação de testes unitários.
* `docs:` Modificações na documentação ou no relatório LaTeX SBC.
* `refactor:` Alterações de código sem impacto no comportamento externo.

### 3. Regras de Pull Request (PR) e Revisão de Código
* **Destino:** Todo PR deve apontar para a branch `develop`.
* **Identificação:** O título e descrição devem conter o identificador da tarefa correspondente (exemplo: "Resolve T1.2").
* **Aprovação Obrigatória:** É necessária a revisão e aprovação formal de pelo menos 1 outro colega de grupo antes do *merge*.
* **Compilação e Testes:** Nenhum PR é integrado caso a compilação falhe ou os testes unitários não passem em 100%.
* **Restrição Acadêmica:** É estritamente proibida a importação de bibliotecas prontas de grafos (como JGraphT ou NetworkX)[cite: 1]. Cabe ao revisor auditar e rejeitar PRs que descumpram essa norma[cite: 1].

---

## Planejamento em Sprints e Distribuição de Tarefas

O desenvolvimento foi estruturado em 4 Sprints, com níveis de dificuldade categorizados em Fácil, Médio, Difícil e Muito Difícil:

### Sprint 1: Biblioteca de Grafos Base (Etapa 1 - Parte A)
**Meta:** Implementar a API base, restrições e as duas representações em memória[cite: 1, 2].
* [ ] **T1.1 (Model):** Implementar a classe abstrata `AbstractGraph` contendo validações de limites de vértices, pesos padrão e contratos abstratos[cite: 1, 2].
  * Dificuldade: Média
  * Responsável: Membro 1
* [ ] **T1.2 (Model):** Implementar a classe `AdjacencyMatrixGraph` para armazenar arestas direcionadas, laços proibidos e pesos[cite: 2].
  * Dificuldade: Média
  * Responsável: Membro 2
* [ ] **T1.3 (Model):** Implementar a classe `AdjacencyListGraph` com listas/mapas encadeados mantendo pesos e idempotência[cite: 2].
  * Dificuldade: Média
  * Responsável: Membro 3
* [ ] **T1.4 (Model):** Implementar métodos de análise de vizinhança e conectividade: `isConnected()` fraco, `isEmptyGraph()`, `isCompleteGraph()`, convergência, divergência e incidência[cite: 2, 3].
  * Dificuldade: Difícil
  * Responsável: Membro 4
* [ ] **T1.5 (Test):** Criar suíte de testes unitários em JUnit validando criação, laços inválidos, exceções e equivalência estrita de comportamento entre Matriz e Lista[cite: 2, 3].
  * Dificuldade: Difícil
  * Responsável: Membro 5

---

### Sprint 2: Exportação Gephi e Demonstração da Biblioteca (Etapa 1 - Parte B)
**Meta:** Finalização dos entregáveis da Etapa 1[cite: 3].
* [ ] **T2.1 (View/Model):** Implementar `exportToGEPHI(String path)` gerando arquivos GEXF, GraphML ou CSV compatíveis com Gephi, preservando pesos e direções[cite: 2, 3].
  * Dificuldade: Média
  * Responsável: Membro 1
* [ ] **T2.2 (View/Controller):** Desenvolver aplicação de demonstração executando todas as operações da API pública em ambas as estruturas[cite: 3].
  * Dificuldade: Média
  * Responsável: Membro 2
* [ ] **T2.3 (Docs):** Elaborar documentação com decisões arquiteturais, instruções de execução e configuração[cite: 3].
  * Dificuldade: Fácil
  * Responsável: Membro 3
* [ ] **T2.4 (Review):** Auditoria técnica de código, garantia de conformidade às regras do trabalho e submissão formal da Etapa 1[cite: 1, 3].
  * Dificuldade: Média
  * Responsável: Membros 4 e 5

---

### Sprint 3: Coleta e Modelagem da Rede OpenClaude (Etapa 2 - Parte A)
**Meta:** Coleta de dados via API e instanciação de grafos com interações da comunidade[cite: 3, 4].
* [ ] **T3.1 (Controller):** Implementar cliente HTTP para a API do GitHub (tratamento de token via `.env`, paginação, limites de taxa e erros)[cite: 3, 4].
  * Dificuldade: Difícil
  * Responsável: Membro 1
* [ ] **T3.2 (Model):** Criar entidades `User`, `Interaction` e `InteractionType` (pesos: comentário = 2, fechamento = 3, revisão/aprovação = 4, merge = 5) e filtros de bots/auto-interações[cite: 4].
  * Dificuldade: Fácil
  * Responsável: Membro 2
* [ ] **T3.3 (Data):** Salvar respostas brutas em `data/raw/` para permitir reprodução local sem necessidade de novas requisições integrais[cite: 4].
  * Dificuldade: Média
  * Responsável: Membro 3
* [ ] **T3.4 (Controller):** Implementar indexador biunívoco convertendo contas de usuários em inteiros contínuos de `0` até `numVertices - 1`[cite: 2, 4].
  * Dificuldade: Média
  * Respons