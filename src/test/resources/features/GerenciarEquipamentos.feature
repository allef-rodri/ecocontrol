# language: pt

Funcionalidade: Gestão de equipamentos
  Para garantir eficiência energética
  Como gestor de ativos sustentáveis
  Quero validar o ciclo de vida dos equipamentos pela API

  Contexto:
    Dado que a API esteja autenticada

  Cenário: Listar equipamentos cadastrados
    Quando eu enviar uma requisição "GET" para o endpoint "/api/equipamentos/listartodos"
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/equipamentos/listar-equipamentos.schema.json"
    E a resposta deve conter ao menos 1 itens

  Cenário: Cadastrar, consultar e excluir um equipamento
    Quando eu enviar uma requisição "POST" para o endpoint "/api/equipamentos" com o corpo:
      """
      {
        "deEquipamento": "Medidor Inteligente ESG",
        "tipo": "SENSOR",
        "consumoMaximo": 150.0,
        "status": "ATIVO",
        "idSetor": 1
      }
      """
    Então o status code da resposta deve ser 201
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/equipamentos/equipamento.schema.json"
    E armazeno o valor do campo "idEquipamento" como "equipamentoId"
    E o campo "status" deve possuir o valor "ATIVO"
    Quando eu enviar uma requisição "GET" para o endpoint "/api/equipamentos/{id}" utilizando o id armazenado em "equipamentoId"
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/equipamentos/equipamento.schema.json"
    Quando eu enviar uma requisição "DELETE" para o endpoint "/api/equipamentos/{id}" utilizando o id armazenado em "equipamentoId"
    Então o status code da resposta deve ser 204
    Quando eu enviar uma requisição "GET" para o endpoint "/api/equipamentos/{id}" utilizando o id armazenado em "equipamentoId"
    Então o status code da resposta deve ser 404
    E a resposta deve conter a mensagem "Equipamento com ID"

  Cenário: Impedir cadastro de equipamento com dados inválidos
    Quando eu enviar uma requisição "POST" para o endpoint "/api/equipamentos" com o corpo:
      """
      {
        "deEquipamento": "",
        "tipo": "SENSOR",
        "consumoMaximo": 150.0,
        "status": "ATIVO",
        "idSetor": 1
      }
      """
    Então o status code da resposta deve ser 500
