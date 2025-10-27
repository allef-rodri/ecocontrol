# language: pt

Funcionalidade: Saúde da aplicação
  Para garantir disponibilidade contínua
  Como analista de confiabilidade
  Quero monitorar o endpoint de status da EcoControl

  Cenário: Consultar o status da aplicação
    Dado que a API esteja autenticada
    Quando eu enviar uma requisição "GET" para o endpoint "/"
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/home/health.schema.json"
    E o campo "status" deve possuir o valor "UP"
