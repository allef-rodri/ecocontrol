# language: pt

Funcionalidade: Gestão de setores
  Para promover governança ambiental
  Como coordenador de sustentabilidade
  Quero garantir que os setores sejam mantidos corretamente na API

  Contexto:
    Dado que a API esteja autenticada

  Cenário: Listar setores cadastrados
    Quando eu enviar uma requisição "GET" para o endpoint "/api/setores"
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/setores/listar-setores.schema.json"
    E a resposta deve conter ao menos 1 itens

  Cenário: Cadastrar e atualizar um setor existente
    E defino um identificador aleatório como "nomeSetor"
    Quando eu enviar uma requisição "POST" para o endpoint "/api/setores" com o corpo:
      """
      {
        "deSetor": "Setor <nomeSetor>",
        "localizacao": "Bloco Verde"
      }
      """
    Então o status code da resposta deve ser 201
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/setores/setor.schema.json"
    E armazeno o valor do campo "idSetor" como "setorId"
    Quando eu enviar uma requisição "PUT" para o endpoint "/api/setores/{id}" com o corpo utilizando o id armazenado em "setorId":
      """
      {
        "deSetor": "Setor Atualizado <nomeSetor>",
        "localizacao": "Bloco Azul"
      }
      """
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/setores/setor.schema.json"
    E o campo "localizacao" deve possuir o valor "Bloco Azul"

  Cenário: Impedir atualização de setor inexistente
    Quando eu enviar uma requisição "PUT" para o endpoint "/api/setores/9999" com o corpo:
      """
      {
        "deSetor": "Setor Fantasma",
        "localizacao": "Bloco X"
      }
      """
    Então o status code da resposta deve ser 500
    E a resposta deve conter a mensagem "Erro ao atualizar setor"

  Cenário: Impedir acesso não autenticado aos setores
    Quando eu enviar uma requisição "GET" sem autenticação para o endpoint "/api/setores"
    Então o status code da resposta deve ser 401
