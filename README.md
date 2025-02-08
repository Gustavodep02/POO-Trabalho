# 📖💻 Sistema de Gestão de Livros e Equipamentos

Este é um projeto Java que implementa dois CRUDs (Create, Read, Update, Delete): um para gestão de **livros** e outro para **equipamentos**. O sistema utiliza **MariaDB** como banco de dados e a interface gráfica foi desenvolvida com **JavaFX**, exibindo os dados em componentes `TableView`.

## 🛠️ Tecnologias Utilizadas
- **JavaFX** para a interface gráfica
- **MariaDB** como banco de dados
- **JDBC** para conexão com o banco

## 📌 Funcionalidades
- Cadastro, edição, exclusão e listagem de **livros**
- Cadastro, edição, exclusão e listagem de **equipamentos**
- Exibição dos registros em **TableView**
- Conexão com **MariaDB** via **JDBC**

## 📑 Configuração do Banco de Dados
1. Instale o **MariaDB** caso não tenha.
2. O script `database.sql` está localizado na raiz do projeto e contém a configuração do banco de dados:
```sql
CREATE DATABASE bibliotecadb;

USE bibliotecadb;

CREATE TABLE livro (
    id INT AUTO_INCREMENT NOT NULL,
    titulo varchar(100),
    autor varchar(100),
    genero varchar(100),
    primary key(id)
);

CREATE TABLE equipamento(
    id int AUTO_INCREMENT NOT NULL,
    nome varchar(100),
    fabricante varchar(100),
    numero_serie varchar (20),
    primary key (id)
);
```
3. Configure as credenciais no arquivo de conexão (`EquipamentoDAOImpl.java` e `LivroDAOImpl.java`):
```java
private static final String URL = "jdbc:mariadb://localhost:3306/bibliotecadb";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

## 🚀 Como Executar o Projeto
1. Clone este repositório:
   ```sh
   git clone https://github.com/Gustavodep02/POO-Trabalho.git
   ```
2. Abra o projeto em sua **IDE** (IntelliJ, Eclipse, NetBeans, etc.).
3. Execute o script `database.sql` no MariaDB para criar as tabelas.
4. Compile e execute `Main.java`.

