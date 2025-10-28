# Getting Started

## Testes Automatizados com BDD

Os cenários de testes comportamentais foram implementados com Cucumber e RestAssured para validar todas as APIs expostas pela EcoControl.

- Pré-requisito: certifique-se de dar permissão de execução para o wrapper do Maven na primeira utilização com `chmod +x mvnw`.
- Execução dos testes: `./mvnw test`
- Os testes sobem a aplicação Spring Boot com o profile `test`, utilizando um banco H2 em memória e dados de carga definidos em `src/test/resources/data.sql`.
- As chamadas autenticadas usam o usuário `analista/analista123` criado automaticamente durante a inicialização do contexto de testes.
- Os cenários BDD estão em `src/test/resources/features` e os contratos JSON em `src/test/resources/schemas`.
- Cada funcionalidade conta com pelo menos um cenário positivo e um cenário negativo para cobrir caminhos felizes e de falha (veja os `.feature` para os detalhes de autenticação, validação de dados e erros de negócio).

Ao final da execução, o relatório padrão do Cucumber é exibido no console. Para pipelines de CI, basta reutilizar o mesmo comando.

## Execução com Docker

O projeto inclui um `Dockerfile` multi-stage e um `docker-compose.yml` para subir a API em modo containerizado.

1. Certifique-se de que o Docker Desktop ou engine equivalente está em execução.
2. Na raiz do repositório, construa e inicialize o serviço:
   `docker compose up --build -d`
3. A aplicação ficará disponível em `http://localhost:8080` (porta exposta no compose).
4. Para acompanhar os logs:
   `docker compose logs -f`
5. Quando terminar, pare o container:
   `docker compose down`

 Por padrão, o `docker-compose.yml` reforça o profile `dev` já definido em `src/main/resources/application.properties` (que aponta para o Oracle da FIAP). Ajuste as variáveis `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` e `SPRING_DATASOURCE_PASSWORD` diretamente no compose, via `docker compose --env-file` ou criando novos profiles em `src/main/resources` caso precise apontar para outro banco.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.5/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.5/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.5/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/3.4.5/reference/io/validation.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.5/reference/web/servlet.html)
* [Flyway Migration](https://docs.spring.io/spring-boot/3.4.5/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.5/reference/using/devtools.html)
* [Spring Security](https://docs.spring.io/spring-boot/3.4.5/reference/web/spring-security.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
