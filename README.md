
# Ocorrências - Sistema de Gestão de Ocorrências

## Descrição

Este projeto é um sistema de gestão de ocorrências, desenvolvido com o objetivo de permitir o cadastro, atualização e exclusão de ocorrências, clientes e endereços. A aplicação utiliza o Spring Framework para o backend, com uma estrutura RESTful e suporte para autenticação via JWT.
## O que falta ser feito a seguir?

- Envio de imagens e hospedagem no Min.io
- Containerização da aplicação




## Funcionalidades

- **Gerenciamento de Clientes**: Cadastro, atualização e exclusão de clientes.
- **Gerenciamento de Endereços**: Cadastro, atualização e exclusão de endereços.
- **Gerenciamento de Ocorrências**: Cadastro, atualização e exclusão de ocorrências relacionadas a clientes e endereços.
- **Autenticação**: Suporte para autenticação de usuários utilizando JWT.
- **Documentação da API**: Documentação automatizada usando Swagger.

## Tecnologias Utilizadas

- **Java**: Versão 17
- **Spring Boot**: Versão 3.2.10
- **Spring Security**: Para autenticação e autorização
- **Spring Data JPA**: Para interação com o banco de dados
- **Swagger**: Para documentação da API
- **Banco de Dados**: PostgreSQL

## Pré-requisitos

 - Java 17
 - Maven
 - IDE de sua escolha (IntelliJ, Eclipse, etc.)
 - Banco de Dados PostgreSQL


## Instalação

1. **Clone o repositório:**

   ```bash
   git clone git clone https://github.com/luizhenrique3651/Ocorrencias.git
   cd ocorrencias
   ```

2. **Instale as dependências:**

   ```bash
   mvn install
   ```

3. **Configuração do Banco de Dados:**

   Configure o banco de dados no arquivo `application.properties`, é necessário ter um banco de dados postgree instalado:

   ```properties
    spring.application.name=ocorrencias
    spring.datasource.url=jdbc:postgresql://localhost:5432/ocorrencias?createDatabaseIfNotExist=true
    spring.datasource.username=SEUUSUARIO
    spring.datasource.password=SUASENHA
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    api.security.token.secret=${JWT_SECRET:minha-chave-secreta-default}
    flyway.locations=classpath:db/migration
   ```
   
   Crie o database ocorrencia pelo seguinte script e o flyway cuidará do restante das tabelas:
	```
	DO $$
	BEGIN
		IF NOT EXISTS (
			SELECT FROM pg_catalog.pg_database
			WHERE datname = 'ocorrencias'
		) THEN
			CREATE DATABASE ocorrencias;
		END IF;
	END $$;
	
	

4. **Executando a aplicação:**

   ```bash
   mvn spring-boot:run
   ```

## Uso da API

A API pode ser acessada através do seguinte endpoint base:

```
http://localhost:8080
```

### Endpoints Principais

- **Autenticação**
  - `POST /auth/login`: Login com usuario e senha
  - `POST /auth/registrar`: Cadastra um novo usuario com usuario, senha e role
- **Clientes**
  - `GET /cliente`: Lista todos os clientes
  - `POST /cliente`: Cadastra um novo cliente
  - `PUT /cliente/{id}`: Atualiza um cliente existente
  - `DELETE /cliente/{id}`: Deleta um cliente

- **Endereços**
  - `GET /endereco`: Lista todos os endereços
  - `POST /endereco`: Cadastra um novo endereço
  - `PUT /endereco/{id}`: Atualiza um endereço existente
  - `DELETE /endereco/{id}`: Deleta um endereço

- **Ocorrências**
  - `GET /ocorrencia`: Lista todas as ocorrências
  - `GET /ocorrencia/filtradas`: Lista todas as ocorrências filtradas
  - `POST /ocorrencia`: Cadastra uma nova ocorrência
  - `POST /ocorrencia/finalizar/{id}`: Seta uma ocorrência com status de finalizada.
  - `PUT /ocorrencia/{id}`: Atualiza uma ocorrência existente
  - `DELETE /ocorrencia/{id}`: Deleta uma ocorrência


## Documentação

A documentação da API está disponível através do Swagger na seguinte URL:

```
http://localhost:8080/swagger-ui/index.html
```

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).



