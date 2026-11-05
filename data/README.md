# Armazenamento de Dados Locais (data/)

Diretório destinado à persistência local de dados coletados do GitHub e exportações geradas pela biblioteca de grafos.

##  Subdiretórios

* **`raw/`**: Contém arquivos JSON ou CSV extraídos via API pública do repositório OpenClaude (issues, pull requests, revisões e comentários).
  * *Observação:* O armazenamento local é obrigatório para permitir a reprodução integral das análises sem necessidade de nova coleta via API do GitHub.
* **`gephi/`**: Contém os grafos gerados e exportados pela aplicação em formato legível pelo software Gephi (GEXF, GraphML ou CSV).
  * `discussoes.gexf`: Grafo de comentários em issues e PRs.
  * `gerenciamento_issues.gexf`: Grafo de abertura e fechamento de issues.
  * `revisao_codigo.gexf`: Grafo de revisões, aprovações e merges.
  * `consolidado.gexf`: Grafo unificado com pesos agregados.