# Desafio Full-Stack UpFlux

O objetivo era desenvolver a proposta no decorrer de 8h. E foi executado no dia 2021-06-03 das 10h as 18h.

## Regras

### Teste de codificação biblioteca   

- [x] Criar uma API para CRUD em uma entidade livro no seguinte endpoints <host>/book/id com apenas 3 informações (id:number, title:String e Autor:String) 
- [x] Criar uma segunda API para registrar empréstimos dos livros <host>/book/{id}/loan/ apenas 3 informações (user:String, borrowed:date returned:date)   

  
### Deve ter   
- [x] Disponibilizar o código fonte em um repositório;   
- [x] Solução escrita em C# ou Java;   
- [ ] Testes unitários automatizados;   
- [x] Código legível;  
- [x] Readme sobre como rodar e utilizar o projeto;  
- [x] Armazenar os dados em um banco de dados orientado a documentos;  
- [x] Pesquisar todos os livros por qualquer 1 dos 3 campos;  
- [x] Visualizar se o livro está disponível;  
- [x] Listar todos os livros ordenando pelos com mais empréstimos;  

### Desejável   
- [ ] User interface em Angular para os 2 CRUD’s;   
- [ ] Disponibilizar o projeto executável em alguma cloud, como Amazon;  

## Concluido

- Foi concluído o desenvolvimento da API. Porém faltou tempo para desenvolver a aplicação Frontend que consumiria essa API. Se houvesse tempo seria desenvolvida em Angular 12 e Bootstrap.
- O banco de dados foi disponibilizaddo em um Docker.

## Como executar

1. Ter instalado [Java](https://openjdk.java.net/) e [Maven](https://maven.apache.org/) 
2. Ter instalado [Docker](https://www.docker.com/)
3. Ter instalado o [Postman](https://www.postman.com/)
4. Clonar esse repositorio
```
git clone https://github.com/lapavalgas/desafio-upflux
``` 
5. Executar na **raiz** do projeto o comando:
```
docker-compose up
```
6. Executar no diretório **/apis** o comando:
```
mvn spring-boot:run
```
7. Importar a [collection](https://github.com/lapavalgas/desafio-upflux/blob/main/Desafio%20Full-Stack%20UpFlux.postman_collection.json) para o Postman
8. Executar as rotas conforme.


