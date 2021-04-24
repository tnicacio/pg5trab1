
# Programação 5 - Trabalho 1
Utilização de JSF para criação de duas páginas para manipulação de dados.
A primeira contém um formulário para cadastro de produtos, inserindo ou atualizando-os no banco de dados.<br/>
Enquanto a segunda página é responsável tanto pela visualização dos produtos, que podem ser filtrados pela descrição/nome ou categoria, quanto pelo redirecionamento do produto selecionado para a sua edição ou exclusão.<br/>
Na página de visualização de dados, os produtos podem ser filtrados pela descrição/nome ou categoria.

## Funcionamento
Na página de Consultas pode-se buscar pelo produto através da sua descrição e, opcionalmente, também pela categoria. <br/>
Para editar um dos produtos listados, pode-se clicar no botão 'editar', e o usuário será redirecionado para a página de Cadastros com os dados atuais do produto já preenchidos. Ao clicar em salvar este produto será atualizado no banco de dados, e ao clicar em cancelar o usuário irá voltar para a tela de Consultas.<br/>
Ao clicar-se em 'excluir' o produto será deletado do banco de dados. <br/>
A criação e inserção de um novo produto consiste-se em navegar para a página de Cadastros, preencher todos os campos do formulário e clicar no botão Salvar. Após o salvamento o usuário será redirecionado para a página de Consultas e lá a listagem dos produtos já irá apresentar o novo produto recém-criado. 
No vídeo abaixo, pode-se observar o funcionamento da aplicação: <br/><br/>
https://user-images.githubusercontent.com/50798315/115975400-de3a9700-a53a-11eb-92cf-2de99d064b23.mp4

## Modelo Lógico
O modelo consiste em duas tabelas: Produto e Categoria. Em que cada produto deve conter uma categoria.<br/><br/>
![modelo-logico-1](https://user-images.githubusercontent.com/50798315/115975395-d7ac1f80-a53a-11eb-8165-fa94fcdd4cd0.png)

## Tecnologias utilizadas
- [Netbeans 12.0](https://netbeans.apache.org/download/nb120/nb120.html)
- [pgAdmin 4](https://www.pgadmin.org/download/)
- [PostgreSQL JDBC Driver 42.2.20](https://jdbc.postgresql.org/)
- [Glassfish 5.0](https://javaee.github.io/glassfish/download)
- [JDK 1.8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- [JSF 2.3](http://www.javaserverfaces.org/)
