
# Ocorrências - Sistema de Gestão de Ocorrências

## Descrição

Este projeto é um sistema de gestão de ocorrências, desenvolvido com o objetivo de permitir o cadastro, atualização e exclusão de ocorrências, clientes e endereços. A aplicação utiliza o Spring Framework para o backend, com uma estrutura RESTful e suporte para autenticação via JWT. Tudo isso executado dentro de um container Docker.
## O que falta ser feito a seguir?

- Envio de imagens e hospedagem no Min.io





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
   git clone https://github.com/luizhenrique3651/Ocorrencias.git
   cd ocorrencias
   ```

2. **Configuração e execução com Docker:**

   Com o Docker e Docker Compose instalados, execute o ambiente completo (banco de dados e aplicação) com o comando:

   ```bash
   docker-compose up -d
   ```

   Esse comando:
   - Sobe um container com o PostgreSQL configurado com o banco de dados `ocorrencias` e as credenciais definidas.
   - Inicializa o serviço da aplicação Spring Boot, conectando-o automaticamente ao banco de dados.

3. **Acessando a aplicação:**

   - A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)
   - O banco de dados PostgreSQL estará disponível na porta `5433` localmente, caso precise acessá-lo diretamente.

4. **Parando o ambiente:**

   Para desligar os containers, use:

   ```bash
   docker-compose down
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



