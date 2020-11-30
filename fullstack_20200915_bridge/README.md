
# Desafio Desenvolvedor Full Stack 2020.2

```Leia-me antes de testar``` 
## Para testar
```
1. Clonar o projeto
2. Abrir um terminal CLI na pasta raiz contendo o arquivo 'docker-compose.yml' (é necessário ter o docker instalado!)
3. Digitar: 'docker-compose up --build' 
```

## Sobre

O desafio desenvolvedor Full Stack 2020.2 é uma etapa do processo seletivo no Laboratório Bridge. Neste desafio, foi solicitado o desenvolvimento de uma aplicação full stack com um serviço backend capaz de se conectar com a API do Github, organizar os dados e apresentar em uma aplicação frontend. Maiores detalhes no [documento anexado](https://github.com/lapavalgas/desafio_fullstack_bridge/blob/master/resources/Desafio%20Dev.%20Full%20Stack%20-%20CLT%202020_2.pdf). O início do desafio ocorreu entre os dias 11 até dia 15 de setembro de 2020, contabilizando três a quatro dias de desenvolvimento corrido. 

## As tecnologias envolvidas

Foi exigida a implementação REST com stack Java para backend e React para frontend. Implementação em Docker, se possível e necessário que a aplicação frontend se comunicasse apenas com o backend.

### As tecnologias de REST e Backend

Aplicações de serviço REST são construídas sobre o protocolo HTTP e não renderizam páginas HTML no servidor. É característico desse tipo de aplicação a comunicação através de objetos JSON que possibilitam uma forma de comunicação mais simples se comparado ao formato anterior de XML. Nesse desafio, o backend em Java/Spring foi construído tentando seguir esse padrão de projeto. 
Java é uma linguagem de programação padrão para o desenvolvimento de qualquer tipo de aplicativo, atualmente na versão 11 LTS. 
Spring é um framework para Java e costuma fornecer um conjunto de estruturas e bibliotecas que tem por finalidade agilizar o desenvolvimento das aplicações.
Para a aplicação backend também foi utilizado o JPA para fornecer a conexão com banco de dados MySQL e armazenar os dados que o usuário poderia favoritar. O MySQL É um banco de dados relacional. JPA é uma implementação de ORM para Java. ORM é uma especificação técnica que esclarece as melhores maneiras de efetuar conexões com banco de dados de forma a manter a integridade e segurança dos dados e conexão. A versão do JPA utilizada foi o Spring Data JPA que é uma abstração do Hibernate, sendo esse último uma das implementações de JPA mais conhecidas para Java. 

### As tecnologias de React e Frontend

O React é um framework JavaScript para construção de aplicações frontend. O propósito desse framework é compreender toda a estrutura de HTML como JavaScript através da syntax JSX. Com essa estrutura é possível pensar a aplicação frontend, ou os componentes utilizados na página de maneira funcional. JavaScript foi uma linguagem de programação que surgiu junto dos primeiros navegadores para auxiliar na criação de páginas dinâmicas e os frameworks propõem diferentes formas de trabalho com o JavaScript em relação ao CSS e HTML.

### As tecnologias de Infraestrutura

O Docker é uma forma de compartilhar recursos de hardware com múltiplas máquinas virtuais, no entanto, diferente do VirtualBox e similares, o Docker permite que cada aplicação esteja isolada sem a necessidade de abrir novas máquinas virtuais, o que significa uma redução considerável no custo de processamento do hardware envolvido. Assim é possível construir e modificar com facilidade qualquer infraestrutura desejada para uma certa aplicação. Nesse desafio foi utilizado o Docker para disponibilizar o Maven (gerenciador de dependências padrão do Java), OpenJDK-11 (a JVM para compilar e interpretar o código Java) e o Spring que internamente já disponibiliza, configura e lança um Tomcat (servidor de aplicações). Além disso, foi utilizado uma imagem Docker de node (interpretador e compilador de códigos JavaScript) com uma instalação padrão do React. Para a orquestração dos containers Docker foi utilizado o Docker Compose que possibilita configurar e lançar múltiplas imagens simultaneamente. 

#### Como funciona?

Após clonar a aplicação e abrir um terminal CLI na pasta raiz contendo o arquivo ``` docker-compo.yml ``` e digitar: ```docker-compose up --build ```

## Planejamento e Execução 

Primeiro foi feito um planejamento do escopo geral da aplicação, distribuição do tempo, elaboração dos wireframes ([Link](https://github.com/lapavalgas/desafio_fullstack_bridge/tree/master/resources)) seguido da montagem da infraestrutura com Docker. Depois, a construção da infraestrutura de Controllers e Models do backend e estilização inicial com CSS. Por fim, integração com banco de dados e a aplicação React.

### O que foi alcançado com sucesso, falhas e contratempos...

De maneira geral *o desafio me venceu*, pois não foi possível entregar tudo que foi solicitado. Acreditando que não haveria dificuldades para integrar o frontend com o backend, construí todo o backend para depois o frontend e por fim integrar. No percurso ocorreram falhas na conexão com a internet que impactaram consideravelmente, além da necessidade de reconfigurar o Docker. O framework React foi meu maior desafio, pois apesar de conhecer a ferramenta não havia ainda desenvolvido com ela. 

#### O que foi desenvolvido com sucesso:

Aplicação REST para pesquisar no Github e especificar linguagem de pesquisa: 
```
GET:localhost:8080/search/{palavra} 
GET:localhost:8080/search/{palavra}/language
``` 
Um serviço de autenticação de usuário sem sistemas de segurança, para que servisse de base para o cadastro e autenticação de usuários e para favoritar os repositórios pelos usuários:  
```
POST: localhost:8080/signin esperando 
request.body JSON ```{"user":"rafael", "pass":"queria_ser_bridge"}`
(utilizando dois Cookies para falsear a autenticação):

POST: localhost:8080/signout/{userLogin} 
request.body JSON {"user":"rafael", "pass":"queria_ser_bridge"}

GET: localhost:8080/{userLogin}/id/{id}

PUT: localhost:8080/{userLogin}/login
request.body JSON {"id":"2", "name":"rafael", "login":"rafael","pass":"queroserbridge"}

POST: localhost:8080/{userLogin}/favorites 
request.body JSON {"bridgeUserId":"26", "favoriteGithubRepositoryUrl":"https://"}

PUT: localhost:8080/{userLogin}/favorites/{userId}

DELETE: localhost:8080/{userLogin}/favorites{favoritesId}
``` 
[Arquivo do Postman](https://github.com/lapavalgas/desafio_fullstack_bridge/blob/master/resources/bridge_fullstack.postman_collection.json) e que foi utilizado para testes no decorrer do desenvolvimento.

### O que não foi de fato alcançado e por quê?

Não foi possível concluir a aplicação Frontend, a estilização do CSS e integração com API. Ocorreram alguns problemas: 
1. Falhas inesperadas na configuração do Docker. 
2. Estava mais enferrujado do que imaginava com estilização em CSS. 
3. E foi a primeira vez que utilizei o React para desenvolver o frontend.

## Imagem da Aplicação

Aqui uma imagem da aplicação, é possível visualizar através do React, porém não está funcionando apropriadamente.

![Imagem da Aplicação](https://github.com/lapavalgas/desafio_fullstack_bridge/blob/master/resources/frontend.png)

---

## Imagens dos Protótipos

Essas são as imagens do protótipo demonstrando em que ponto pretendia chegar.

![Imagem do Wireframe](https://github.com/lapavalgas/desafio_fullstack_bridge/blob/master/resources/Documenta%C3%A7%C3%A3o/pesquisa_autenticada_2_-_repositorio_selecionado.svg)

![Imagem do Wireframe](https://github.com/lapavalgas/desafio_fullstack_bridge/blob/master/resources/Documenta%C3%A7%C3%A3o/pesquisa_autenticada.svg)

[Link para o wireframes](https://github.com/lapavalgas/desafio_fullstack_bridge/tree/master/resources/Documenta%C3%A7%C3%A3o)
