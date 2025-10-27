# language: pt

Funcionalidade: Gestão de alertas
  Para monitorar o uso de energia com responsabilidade
  Como analista da EcoControl
  Quero validar que os alertas são listados corretamente pela API

  Cenário: Listar todos os alertas com sucesso
    Dado que a API esteja autenticada
    Quando eu enviar uma requisição "GET" para o endpoint "/api/alertas/listartodos"
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve corresponder ao esquema JSON "schemas/alertas/listar-alertas.schema.json"
    E a resposta deve conter ao menos 1 itens no campo "content"
