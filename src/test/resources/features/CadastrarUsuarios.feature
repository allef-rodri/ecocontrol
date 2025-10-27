# language: pt

Funcionalidade: Cadastro de usuários
  Para garantir governança e acesso seguro
  Como administrador do sistema
  Quero validar o cadastro de usuários na plataforma

  Contexto:
    Dado que a API esteja autenticada

  Cenário: Cadastrar um novo usuário com credenciais válidas
    E defino um identificador aleatório como "loginUnico"
    Quando eu enviar uma requisição "POST" para o endpoint "/usuarios" com o corpo:
      """
      {
        "login": "usuario.<loginUnico>",
        "senha": "SenhaSegura123",
        "role": "ADMIN"
      }
      """
    Então o status code da resposta deve ser 201
