<h1 align="center">
  <br>
  <img src="https://camo.githubusercontent.com/fd2ef592ee28d265250a1fc3ecdeef46ea004bde9ee4d4f8dddc0d9a49ae361d/68747470733a2f2f6f617574682e6c65626973637569742e696f2f696d616765732f6c6f676f2e6a7067" alt="Markdownify" width="200">
  <br>
  Desafio - Intenção de Compra
  <br>
</h1>

<h4 align="center">Desafio para Seleção de Desenvolvedor Back-End</h4>

<p align="center">
  <a href="https://badge.fury.io/js/electron-markdownify"></a>
</p>

<p align="center">
  <a href="#descrição">Descrição</a> •
  <a href="#requisitos">Requisitos</a> •
  <a href="#observações">Observações</a> •
  <a href="#guia">Guia</a> •
</p>

## Descrição

Deverão ser desenvolvidos dois serviços que, juntos, gerenciam a intenção de compra dos usuários.

## Requisitos

### Serviço de Produtos

1. Deverá ser desenvolvido em PHP ou TypeScript usando qualquer framework.
2. Pode ser Rest ou GraphQL.
3. Deve ter no mínimo 1 endpoint (Query ou Mutation) para listar produtos e 1 endpoint (Query ou Mutation) para criar intenção de compra.
4. A listagem de produtos deve buscar os dados internamente da [Fake Store API](https://fakestoreapi.com/docs).
5. O endpoint para criação de intenção de compra deve enviar os dados para o **Serviço de Intenção**, que realizará as operações.
6. Este serviço **NÃO PODE USAR NENHUM BANCO DE DADOS**.

### Serviço de Intenção

1. Deverá ser desenvolvido em qualquer linguagem, exceto aquela usada no **Serviço de Produtos**.
2. Pode ser Rest ou GraphQL.
3. Deve ter no mínimo 1 endpoint (REST ou GraphQL) para criar intenção e 1 endpoint (REST ou GraphQL) para listar intenções de compra.
4. Este serviço deve usar no mínimo 1 banco de dados (MongoDB ou MySQL). Caso necessário, outros bancos de dados podem ser adicionados, sendo a escolha livre.
5. A intenção de compra deve incluir: nome do cliente, produtos e endereço do cliente.

### Infraestrutura

1. O banco e os sistemas devem estar containerizados.
2. Toda a infraestrutura deve estar online na máquina local com, no máximo, 10 comandos em qualquer máquina Linux com Docker instalado.
3. O Docker/Podman deve ser utilizado como base da infraestrutura.

## Observações

* Todos os Recursos devem estar nesse único Repositório
* Não é necessário e não será avaliado nenhum recurso de Front-End
* Os Padrões de Projetos e Tecnologias Aplicadas serão avaliadas
* A Modelagem de Banco e estratégias de Manipulação de Dados serão Avaliadas
* Não é Obrigatório ter Documentação mas a facilidade no uso dos recursos será avaliada e a presença de uma documentação simplificada pode impactar positivamente ou negativamente.
* A Organização do Repositório será avaliada
* Os Commits serão avaliados, atente-se aos padrões que deseja utilizar
* Os endpoints de listagem de dados podem ter recursos adicionais (filtragem e paginação) (opcionais), que serão avaliados.

## Guia

1. Faça um Fork Privado do Repositório
2. Desenvolva os Recursos
3. Conceda Permissão de Leitura para Membros Indicados
4. Aguarde a Avaliação
