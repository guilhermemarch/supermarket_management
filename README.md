
# Projeto Carrinho

## Descrição

O **Projeto Carrinho de Mercado** é uma aplicação desenvolvida para gerenciar um sistema de estoque e carrinho de compras. O objetivo principal é permitir que os usuários adicionem, removam e visualizem produtos em estoque, além de gerenciar os produtos no carrinho de compras.

## Funcionalidades

### Gerenciar Estoque
- **Adicionar produtos ao estoque.**
- **Remover produtos do estoque.**
- **Consultar produtos por ID.**
- **Visualizar todo o estoque disponível.**

### Gerenciar Carrinho
- **Adicionar produtos ao carrinho.**
- **Remover produtos do carrinho.**
- **Exibir o conteúdo do carrinho.**
- **Limpar o carrinho.**

### Finalizar Compra
- **Atualizar o estoque com os produtos comprados.**
- **Limpar o carrinho após a finalização da compra.**

## Estrutura do Projeto

O projeto é dividido em várias classes:

- **Program:** Classe principal que gerencia a interação do usuário e apresenta o menu principal.
- **Menu:** Classe responsável por gerenciar as opções do menu para estoque e carrinho.
- **Estoque:** Classe que manipula os produtos em estoque e interage com o banco de dados.
- **Carrinho:** Classe que manipula os produtos no carrinho de compras e interage com o banco de dados.
- **Produto:** Classe que representa os produtos com atributos como ID, nome, categoria, valor e quantidade.

## Tecnologias Utilizadas

- **Java**
- **JDBC (Java Database Connectivity)** para interação com o banco de dados.
- **Banco de dados** (MySQL)

## Instalação

### Configuração do Banco de Dados

1. **Certifique-se de que o MySQL está instalado e em execução.**
2. **Para funcionar, deve ser feita conexão, que esta no arquivo /resources/mysql-connector**
3. **Após isso, abra o script `script.txt` (localizado na pasta do projeto) para criar as tabelas e inserir os dados necessários:**

   ```sql
   \ProjetoCarrinho\src\main\resources\script.txt

### Configuração do Projeto

**No arquivo `db.properties`, configure as informações de conexão com o banco de dados:**

```java
String url = "jdbc:mysql://localhost:3306/seuBanco";
String user = "seuUsuario";
String password = "suaSenha";
```

## Compilação do Projeto
Navegue até a pasta do projeto e execute o seguinte comando para compilar:
javac -d bin src/main/java/carrinho/**/*.java

## Execução do Projeto
Execute o programa a partir da classe principal Program:
java -cp bin carrinho.Program

## Estrutura de Pastas
/src - Contém os arquivos-fonte.  
/bin - Contém os arquivos compilados (.class).  
/resources - Contém o script de banco de dados e a configuração do banco de dados.  
/entidades - Contém as classes de entidade, que representam os modelos de dados principais, como Produto.  
/servico - Contém a classe Program.java, onde está o método main que executa o programa.  
/db - Contém as classes de acesso ao banco de dados, responsáveis pela conexão e execução de consultas ao banco de dados. 

## Experiência de Desenvolvimento

No início, enfrentei algumas dificuldades para configurar a conexão com o banco de dados MySQL usando JDBC, mas, ao resolver esses problemas, ganhei muita experiência prática. Aprender a gerenciar o estoque e o carrinho de compras de forma independente, garantindo que o sistema fosse atualizado corretamente, foi um desafio intenso.  
No segundo dia, o projeto estava tão presente que cheguei a sonhar com ele! 😆  
Ao longo do desenvolvimento, aprendi boas práticas e percebi a importância de uma documentação clara para garantir que o projeto seja compreensível e sustentável. Essa experiência foi um grande aprendizado técnico e me preparou para enfrentar desafios ainda mais complexos no futuro.  




