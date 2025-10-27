# language: pt

Funcionalidade: Monitoramento de leituras de consumo
  Para garantir eficiência energética contínua
  Como analista de dados ESG
  Quero validar o registro e consulta das leituras de consumo

  Contexto:
    Dado que a API esteja autenticada

  Cenário: Listar leituras registradas
    Quando eu enviar uma requisição "GET" para o endpoint "/leituras"
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/leituras/listar-leituras.schema.json"
    E a resposta deve conter ao menos 1 itens

  Cenário: Registrar uma nova leitura de consumo
    Quando eu enviar uma requisição "POST" para o endpoint "/leituras" com o corpo:
      """
      {
        "kwhConsumido": 175.5,
        "dataHoraLeitura": "2024-10-10T10:30:00",
        "idEquipamento": 1
      }
      """
    Então o status code da resposta deve ser 201
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/leituras/leitura.schema.json"
    E o campo "nomeEquipamento" deve possuir o valor "Medidor Solar"
