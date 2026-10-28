Refatoração do Sistema Bancário - Arquitetura Cliente/Servidor
1. Extração e Seleção de Requisitos
Utilizando como base a análise do sistema legado e as metodologias de extração, definimos um fluxo de negócio contínuo composto por 5 requisitos funcionais interligados:

Cadastrar cliente

Ativar cliente (Aplicando a regra de domínio - DDD)

Cadastrar conta corrente

Transferir valores entre contas (Aplicando a validação mecânica - TDD)

Consultar conta corrente

2. Refatoração e Arquitetura (REST API)
Para modernizar o sistema e separar a camada de apresentação (Cliente) da camada de negócio (Servidor), substituímos o antigo menu monolítico de terminal (Main.java) por uma REST API construída com Java e Spring Boot.
O código-fonte de negócio (as entidades ricas, regras e serviços) foi totalmente preservado e isolado no backend. O cliente (frontend) agora interage com o sistema exclusivamente através de requisições HTTP (GET, POST, PUT), garantindo total desacoplamento e seguindo o padrão de mercado.

3. Teste do Fluxo Principal (Cenário de Integração)
O fluxo principal das funcionalidades foi validado simulando a jornada completa de um usuário consumindo a API:

Passo 1 (POST /api/banco/clientes): Criação do usuário no sistema.

Passo 2 (PUT /api/banco/clientes/{id}/ativar): Mudança de estado do cliente utilizando a entidade rica.

Passo 3 (POST /api/banco/contas): Abertura de contas bancárias para possibilitar operações financeiras.

Passo 4 (POST /api/banco/transferencia): Movimentação financeira validando as travas de saldo insuficiente.

Passo 5 (GET /api/banco/contas/{id}): Consulta final garantindo a consistência dos dados após a transferência.

4. Declaração de Uso de Inteligência Artificial
Conforme as diretrizes da atividade, declaramos o uso da ferramenta Gemini (LLM) durante este processo com os seguintes propósitos:

Análise: Auxílio na extração e correlação dos 5 requisitos lógicos a partir do código legado.

Refatoração: Apoio na transição da arquitetura de console para a estrutura de Controllers REST no padrão Spring Boot, gerando o código boilerplate de mapeamento de endpoints.
