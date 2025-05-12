# EcoControl API

Bem-vindo à documentação da API EcoControl. Esta API é responsável por gerenciar e monitorar dados relacionados ao controle ambiental.

# Guia de Execução do Docker para EcoControl
## 1. Pré-requisitos
Antes de começar, certifique-se de ter instalado:
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/) (geralmente já vem com o Docker Desktop)

## 2. Estrutura do Projeto
O projeto já possui os arquivos necessários para o Docker:
- - Define como a imagem será construída `Dockerfile`
- - Define como os serviços serão executados `docker-compose.yml`
- - Lista arquivos que devem ser ignorados no build `.dockerignore`

## 3. Comandos Básicos
### 3.1. Construir a Imagem
``` bash
docker build -t ecocontrol .
```
Este comando irá:
1. Usar o Maven para compilar o projeto
2. Criar uma imagem otimizada com o JRE
3. Configurar o ambiente de execução

### 3.2. Executar com Docker Compose
``` bash
docker-compose up -d
```
Este comando irá:
1. Iniciar o container em modo detached (background)
2. Expor a porta 8080
3. Configurar as variáveis de ambiente definidas no `docker-compose.yml`

### 3.3. Verificar Status
``` bash
docker ps
```
Isso mostrará se o container está rodando corretamente.
### 3.4. Visualizar Logs
``` bash
docker logs ecocontrol
```
ou em modo contínuo:
``` bash
docker logs -f ecocontrol
```
### 3.5. Parar o Container
``` bash
docker-compose down
```
## 4. Configurações Importantes
### 4.1. Variáveis de Ambiente
No `docker-compose.yml`
- `SPRING_PROFILES_ACTIVE: dev` - Define o perfil ativo

### 4.2. Portas
- A aplicação está configurada para rodar na porta 8080
- O mapeamento de porta está definido como "8080:8080" no `docker-compose.yml`

## 5. Solução de Problemas
Se encontrar problemas:
1. **Container não inicia:**
``` bash
docker logs ecocontrol
```
2. **Problemas de rede:**
``` bash
docker network ls
docker network inspect bridge
```
3. **Limpar ambiente:**
``` bash
docker-compose down
docker system prune
```
4. **Reconstruir sem cache:**
``` bash
docker-compose build --no-cache
docker-compose up -d
```
## 6. Acessando a Aplicação
Após iniciar o container, a aplicação estará disponível em:
- [http://localhost:8080](http://localhost:8080)

Lembre-se das credenciais padrão:
- Usuário: `Allef`
- Senha: `12345`

## Endpoints da API

A seguir, estão listados os endpoints disponíveis na API.

---

### 1. Status da Aplicação

Retorna informações sobre o estado atual da aplicação, incluindo uso de memória e o perfil de ambiente ativo.

-   **Método:** `GET`
-   **Path:** `/`
-   **Autenticação:** Pode requerer Basic Auth (ver Credenciais de Segurança Padrão).

**Parâmetros da Requisição:**
Nenhum.

**Exemplo de Resposta de Sucesso (200 OK):**
```json
{
  "status": "UP",
  "environment": "dev",
  "totalMemory": 536870912,
  "freeMemory": 275173776,
  "maxMemory": 8589934592,
  "usedMemory": 261697136
}
```
**Memória em bytes**

**Respostas de Erro Comuns:**
-   `401 Unauthorized`: Se as credenciais de autenticação não forem fornecidas ou forem inválidas.
-   Outros erros padrão do Spring Boot em caso de falhas internas.

---

### 2. Listar Todos os Alertas

Recupera uma lista paginada de todos os alertas registrados no sistema.

-   **Método:** `GET`
-   **Path:** `/api/alertas/listartodos`
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
`GET http://localhost:8080/api/alertas/listartodos?page=0&size=5&sort=dataHoraAlerta,desc`

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
      }
    }
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

Vou adicionar a documentação dos endpoints de Setor ao EcoControlAPIDocumentation.md:

### 3. Gerenciamento de Setores

Endpoints para gerenciar os setores da aplicação.

#### 3.1. Listar Setores

Retorna todos os setores cadastrados no sistema.

- **Método:** `GET`
- **Path:** `/api/setores`
- **Autenticação:** Basic Auth necessária

**Resposta de Sucesso (200 OK):**
```json
[
    {
    "idSetor": 1,
    "deSetor": "Produção",
    "localizacao": "Bloco A - Térreo"
    },
    {
    "idSetor": 2,
    "deSetor": "Almoxarifado",
    "localizacao": "Bloco B - Subsolo"
    }
]
```
#### 3.2. Detalhar Setor

Retorna os detalhes de um setor específico.

- **Método:** `GET`
- **Path:** `/api/setores/{id}`
- **Autenticação:** Basic Auth necessária

**Resposta de Sucesso (200 OK):**
```json
{
"idSetor": 1,
"deSetor": "Produção",
"localizacao": "Bloco A - Térreo"
}
```
#### 3.3. Cadastrar Setor

Cria um novo setor no sistema.

- **Método:** `POST`
- **Path:** `/api/setores`
- **Autenticação:** Basic Auth necessária

**Corpo da Requisição:**
```json
{
"deSetor": "Produção",
"localizacao": "Bloco A - Térreo"
}
```
**Validações:**
- `deSetor`: obrigatório, máximo 100 caracteres
- `localizacao`: obrigatório, máximo 200 caracteres

**Resposta de Sucesso (201 Created):**
```json
{
"idSetor": 1,
"deSetor": "Produção",
"localizacao": "Bloco A - Térreo"
}
```
#### 3.4. Atualizar Setor

Atualiza os dados de um setor existente.

- **Método:** `PUT`
- **Path:** `/api/setores/{id}`
- **Autenticação:** Basic Auth necessária

**Corpo da Requisição:**
```json
{
"deSetor": "Produção Atualizado",
"localizacao": "Bloco A - 1º Andar"
}
```
**Resposta de Sucesso (200 OK):**
```json
{
    "idSetor": 1,
    "deSetor": "Produção Atualizado",
    "localizacao": "Bloco A - 1º Andar"
}
```

#### 3.5. Excluir Setor

Remove um setor do sistema.

- **Método:** `DELETE`
- **Path:** `/api/setores/{id}`
- **Autenticação:** Basic Auth necessária

**Resposta de Sucesso (204 No Content)**

**Respostas de Erro Comuns para todos os endpoints:**
- `400 Bad Request`: Dados inválidos na requisição
- `401 Unauthorized`: Credenciais de autenticação ausentes ou inválidas
- `404 Not Found`: Setor não encontrado
- `500 Internal Server Error`: Erro interno do servidor

Esta documentação fornece todas as informações necessárias para usar os endpoints de Setor, incluindo:
- Métodos HTTP
- URLs
- Formatos de requisição e resposta
- Códigos de status HTTP
- Exemplos de payload
- Validações
- Possíveis erros

---

### 4. Gerenciamento de Equipamentos

Endpoints para gerenciar os equipamentos da aplicação.

#### 4.1. Listar Equipamentos

Retorna todos os equipamentos cadastrados no sistema.

- **Método:** `GET`
- **Path:** `/api/equipamentos/listartodos`
- **Autenticação:** Basic Auth necessária

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "idEquipamento": 1,
    "deEquipamento": "Ar Condicionado",
    "tipo": "Climatização",
    "consumoMaximo": 1500.00,
    "status": "Ligado",
    "setor": {
      "idSetor": 1,
      "deSetor": "Diretoria",
      "localizacao": "Décimo Andar - Sala 1002"
    }
  },
  {
    "idEquipamento": 2,
    "deEquipamento": "Geladeira",
    "tipo": "Refrigeração",
    "consumoMaximo": 55.00,
    "status": "Ligado",
    "setor": {
      "idSetor": 5,
      "deSetor": "Refeitório",
      "localizacao": "Primeiro Andar"
    }
  }
]
```
#### 4.2. Detalhar Equipamento

Retorna os detalhes de um equipamento específico.

- **Método:** `GET`
- **Path:** `/api/equipamentos/{id}`
- **Autenticação:** Basic Auth necessária

**Parâmetros do Path:**
- `id` (Long, obrigatório): ID do equipamento

**Resposta de Sucesso (200 OK):**
```json
{
  "idEquipamento": 1,
  "deEquipamento": "Ar Condicionado",
  "tipo": "Climatização",
  "consumoMaximo": 1500.00,
  "status": "Ligado",
  "setor": {
    "idSetor": 1,
    "deSetor": "Diretoria",
    "localizacao": "Décimo Andar - Sala 1002"
  }
}
```
#### 4.3. Cadastrar Equipamento

Cria um novo equipamento no sistema.

- **Método:** `POST`
- **Path:** `/api/equipamentos`
- **Autenticação:** Basic Auth necessária

**Corpo da Requisição:**
```json
{
    "deEquipamento": "Ar Condicionado",
    "tipo": "Climatização",
    "consumoMaximo": 1500.00,
    "status": "Ligado",
    "idSetor": 1
}
```
**Validações:**
- `deEquipamento`: obrigatório, máximo 100 caracteres
- `tipo`: obrigatório, máximo 100 caracteres
- `consumoMaximo`: obrigatório, valor positivo
- `status`: obrigatório, máximo 20 caracteres
- `idSetor`: obrigatório, deve referenciar um setor existente

**Resposta de Sucesso (201 Created):**
```json
{
    "idEquipamento": 1,
    "deEquipamento": "Ar Condicionado",
    "tipo": "Climatização",
    "consumoMaximo": 1500.00,
    "status": "Ligado",
    "setor": {
        "idSetor": 1,
        "deSetor": "Diretoria",
        "localizacao": "Décimo Andar - Sala 1002"
    }
}
```
#### 4.4. Atualizar Equipamento

Atualiza os dados de um equipamento existente.

- **Método:** `PUT`
- **Path:** `/api/equipamentos/{id}`
- **Autenticação:** Basic Auth necessária

**Parâmetros do Path:**
- `id` (Long, obrigatório): ID do equipamento

**Corpo da Requisição:**
```json
{
    "deEquipamento": "Ar Condicionado Atualizado",
    "tipo": "Climatização",
    "consumoMaximo": 1800.00,
    "status": "Manutenção",
    "idSetor": 1
}
```
**Resposta de Sucesso (200 OK):**
```json
{
  "idEquipamento": 1,
  "deEquipamento": "Ar Condicionado Atualizado",
  "tipo": "Climatização",
  "consumoMaximo": 1800.00,
  "status": "Manutenção",
  "setor": {
    "idSetor": 1,
    "deSetor": "Diretoria",
    "localizacao": "Décimo Andar - Sala 1002"
  }
}
```
#### 4.5. Excluir Equipamento

Remove um equipamento do sistema.

- **Método:** `DELETE`
- **Path:** `/api/equipamentos/{id}`
- **Autenticação:** Basic Auth necessária

**Parâmetros do Path:**
- `id` (Long, obrigatório): ID do equipamento

**Resposta de Sucesso (204 No Content)**

**Respostas de Erro Comuns para todos os endpoints:**
- `400 Bad Request`: Dados inválidos na requisição
- `401 Unauthorized`: Credenciais de autenticação ausentes ou inválidas
- `404 Not Found`: Equipamento não encontrado
- `500 Internal Server Error`: Erro interno do servidor

---

Este `EcoControlAPIDocumentation.md` deve ser um bom ponto de partida.
- URLs e métodos HTTP
- Parâmetros necessários
- Formatos de requisição e resposta
- Exemplos práticos
- Validações
- Possíveis códigos de resposta