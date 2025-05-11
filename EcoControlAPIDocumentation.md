# EcoControl API

Bem-vindo à documentação da API EcoControl. Esta API é responsável por gerenciar e monitorar dados relacionados ao controle ambiental.

## Configuração Inicial

Para executar esta aplicação, você precisará ter o Java (SDK 22 ou superior) e o Maven configurados em seu ambiente.
As configurações de banco de dados e segurança padrão estão no arquivo `src/main/resources/application.properties`.

**Credenciais de Segurança Padrão (Basic Auth):**
Por padrão, o Spring Security está configurado com um usuário e senha básicos. Você precisará fornecer essas credenciais ao acessar os endpoints protegidos.
-   **Usuário:** `Allef`
-   **Senha:** `12345`

*Observação: Em breve, um sistema de autenticação baseado em token via um `AuthController` dedicado será implementado para uma segurança mais robusta e adequada para APIs.*

## Endpoints da API

A seguir, estão listados os endpoints disponíveis na API.

---

### 1. Status da Aplicação

Retorna informações sobre o estado atual da aplicação, incluindo uso de memória e o perfil de ambiente ativo.

-   **Método:** `GET`
-   **Path:** `/api/status`
-   **Autenticação:** Pode requerer Basic Auth (ver Credenciais de Segurança Padrão).

**Parâmetros da Requisição:**
Nenhum.

**Exemplo de Resposta de Sucesso (200 OK):**
```json
{
  "status": "UP",
  "environment": "dev", // ou o perfil ativo, ex: "dev", "prod"
  "totalMemory": 536870912, // Memória total alocada para a JVM (bytes)
  "freeMemory": 275173776,  // Memória livre dentro da alocada (bytes)
  "maxMemory": 8589934592,   // Memória máxima que a JVM pode usar (bytes)
  "usedMemory": 261697136   // Memória usada (totalMemory - freeMemory) (bytes)
}
```

**Respostas de Erro Comuns:**
-   `401 Unauthorized`: Se as credenciais de autenticação não forem fornecidas ou forem inválidas.
-   Outros erros padrão do Spring Boot em caso de falhas internas.

---

### 2. Listar Todos os Alertas

Recupera uma lista paginada de todos os alertas registrados no sistema.

-   **Método:** `GET`
-   **Path:** `/alertas/listartodos`
-   **Autenticação:** Pode requerer Basic Auth (ver Credenciais de Segurança Padrão).

**Parâmetros da Requisição (Query Parameters opcionais para paginação):**

-   `page` (Integer, opcional): Número da página desejada (começando em 0).
    -   Exemplo: `?page=0`
-   `size` (Integer, opcional): Quantidade de itens por página.
    -   Exemplo: `?size=10`
-   `sort` (String, opcional): Critério de ordenação. Formato: `campo[,asc|desc]`.
    -   Exemplo: `?sort=dataHoraAlerta,desc` (ordena pela data do alerta, da mais recente para a mais antiga)
    -   Exemplo: `?sort=tipoAlerta,asc` (ordena pelo tipo de alerta, ascendente)

**Exemplo de Requisição:**
`GET http://localhost:8080/alertas/listartodos?page=0&size=5&sort=dataHoraAlerta,desc`

**Exemplo de Resposta de Sucesso (200 OK):**
```json
{
  "content": [
    {
      "idAlerta": 1,
      "tipoAlerta": "Temperatura Elevada",
      "mensagem": "Sensor X detectou temperatura acima do limite.",
      "dataHoraAlerta": "2025-05-12T10:30:00",
      "equipamento": {
        "idEquipamento": 101,
        "nomeEquipamento": "Sensor de Temperatura A",
        "localizacao": "Bloco A, Sala 10"
        // Outros campos do EquipamentoExibicaoDto
      }
    },
    {
      "idAlerta": 2,
      "tipoAlerta": "Nível Baixo",
      "mensagem": "Reservatório Y com nível abaixo do crítico.",
      "dataHoraAlerta": "2025-05-12T09:15:00",
      "equipamento": {
        "idEquipamento": 202,
        "nomeEquipamento": "Sensor de Nível B",
        "localizacao": "Bloco C, Área Externa"
        // Outros campos do EquipamentoExibicaoDto
      }
    }
    // ... mais alertas
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 10,
  "totalElements": 50,
  "last": false,
  "size": 5,
  "number": 0,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 5,
  "first": true,
  "empty": false
}
```
*Observação: A estrutura do objeto `equipamento` dentro de cada alerta corresponde ao `EquipamentoExibicaoDto`.*

**Respostas de Erro Comuns:**
-   `401 Unauthorized`: Se as credenciais de autenticação não forem fornecidas ou forem inválidas.
-   Outros erros padrão do Spring Boot em caso de falhas internas.

---

Este `README.md` deve ser um bom ponto de partida. Você pode adicionar mais seções conforme sua API evolui, como:
*   Detalhes sobre o modelo de dados.
*   Como configurar o ambiente de desenvolvimento.
*   Instruções de build e deploy.
*   Endpoints para criação, atualização e exclusão de recursos (CRUD).
*   Informações sobre o `AuthController` quando ele for implementado.