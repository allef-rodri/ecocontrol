# language: pt

  Funcionalidade: Listar todos os alertas
    Como usuário da API de listar todos os alertas
    Quero listar todos os todos os alertas
    Para que que minha aplicação possa exibir os alertas para os usuários

    Cenário: Listar todos os alertas com sucesso
      Dado que a API esteja disponível
      Quando eu enviar uma requisição GET para o endpoint "/api/alertas/listartodos"
      Então o status code da resposta deve ser 200
      E a resposta deve conter uma lista de alertas
      E cada alerta deve possuir os campos "idAlerta", "tipoAlerta", "mensagem", "dataHoraAlerta" e "equipamento"