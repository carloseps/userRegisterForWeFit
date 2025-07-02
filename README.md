# userRegisterForWeFit

Este documento fornece as instruções necessárias para configurar o ambiente de desenvolvimento e executar o projeto `userRegisterForWeFit`.

## 1. Pré-requisitos

Certifique-se de ter os seguintes softwares instalados em sua máquina:

*   **Java Development Kit (JDK) 17 ou superior:** O projeto foi desenvolvido e testado com JDK 17. Você pode verificar sua versão do Java executando `java -version` no terminal.
*   **Apache Maven 3.x:** O Maven é utilizado para gerenciar as dependências do projeto e para construir a aplicação. Você pode verificar sua versão do Maven executando `mvn -version` no terminal.
*   **PostgreSQL:** O projeto utiliza PostgreSQL como banco de dados. Certifique-se de ter uma instância do PostgreSQL em execução e acessível.

## 2. Configuração do Banco de Dados

O projeto utiliza o Flyway para gerenciar as migrações do banco de dados. Antes de executar a aplicação, você precisará configurar as informações de conexão com o banco de dados no arquivo `application.properties`.

1.  Localize o arquivo `application.properties` em `src/main/resources/application.properties`.
2.  Edite as seguintes propriedades com as informações do seu banco de dados PostgreSQL:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/wefit_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

    *   Substitua `localhost:5432` pelo endereço e porta do seu servidor PostgreSQL, se for diferente.
    *   Substitua `wefit_db` pelo nome do seu banco de dados.
    *   Substitua `seu_usuario` e `sua_senha` pelas credenciais do seu usuário do PostgreSQL.

## 3. Construção do Projeto

Para construir o projeto, navegue até o diretório raiz do projeto (`userRegisterForWeFit/userRegisterForWeFit/`) no terminal e execute o seguinte comando Maven:

```bash
mvn clean install
```

Este comando irá compilar o código, executar os testes e empacotar a aplicação em um arquivo JAR executável.

## 4. Execução da Aplicação

Após a construção bem-sucedida do projeto, você pode executar a aplicação Spring Boot a partir do terminal. Navegue até o diretório raiz do projeto (`userRegisterForWeFit/userRegisterForWeFit/`) e execute:

```bash
java -jar target/userRegisterForWeFit-0.0.1-SNAPSHOT.jar
```

A aplicação será iniciada e estará acessível em `http://localhost:8080` (ou na porta configurada no `application.properties`).

## 5. Acesso à Documentação da API (Swagger UI)

O projeto inclui a documentação da API gerada automaticamente com SpringDoc OpenAPI. Após iniciar a aplicação, você pode acessar a interface do Swagger UI em:

```
http://localhost:8080/swagger-ui.html
```

Esta interface permite que você visualize todos os endpoints da API, seus parâmetros de entrada, modelos de dados e execute requisições de teste.

## 6. Execução dos Testes

Para executar apenas os testes automatizados do projeto, navegue até o diretório raiz do projeto (`userRegisterForWeFit/userRegisterForWeFit/`) e execute:

```bash
mvn test
```

Isso executará todos os testes unitários e de integração configurados no projeto.

