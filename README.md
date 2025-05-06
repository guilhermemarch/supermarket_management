# Supermarket Manager

## Description

The **Supermarket Manager** is an application developed to manage an inventory and shopping cart system. The main goal is to allow users to add, remove, and view products in the inventory, as well as manage products in the shopping cart.

## Features

### Inventory Management
- **Add products to the inventory.**
- **Remove products from the inventory.**
- **Search products by ID.**
- **View all available products.**

### Shopping Cart Management
- **Add products to the cart.**
- **Remove products from the cart.**
- **View cart contents.**
- **Clear the cart.**

### Checkout
- **Update the inventory based on purchased products.**
- **Clear the cart after checkout.**

### Command-Line Interface (new)
- Manage inventory and cart via CLI using the `picocli` library.
- Example CLI commands:
  ```bash
  java -cp bin carrinho.cli.SupermarketCLI estoque -l
  java -cp bin carrinho.cli.SupermarketCLI carrinho -a 1 3
  ```
- Available options:
   - `estoque` command:
      - `-a`, `--add`: Add product (requires ID and quantity)
      - `-r`, `--remove`: Remove product (requires ID and quantity)
      - `-l`, `--list`: List all inventory products
   - `carrinho` command:
      - `-a`, `--add`: Add product to cart
      - `-r`, `--remove`: Remove product from cart
      - `-l`, `--list`: List cart contents

## Project Structure

The project is divided into several classes:

- **Program:** Main class that manages user interaction and displays the main menu.
- **Menu:** Handles menu options for inventory and cart.
- **Estoque (Inventory):** Manages inventory products and interacts with the database.
- **Carrinho (Cart):** Manages shopping cart and interacts with the database.
- **Produto (Product):** Represents a product with attributes like ID, name, category, price, and quantity.
- **SupermarketCLI:** Provides a command-line interface using the `picocli` library for interacting with inventory and cart.

## Technologies Used

- **Java**
- **JDBC (Java Database Connectivity)** for database interaction
- **MySQL** as the database
- **Picocli** for building CLI commands

## Installation

### Database Setup

1. **Ensure MySQL is installed and running.**
2. **Connect using the driver provided at `/resources/mysql-connector`.**
3. **Run the SQL script located at:**

   ```
   \ProjetoCarrinho\src\main
esources\script.txt
   ```

   This script creates the necessary tables and inserts initial data.

### Project Configuration

Edit the `db.properties` file with your database credentials:

```java
String url = "jdbc:mysql://localhost:3306/yourDatabase";
String user = "yourUser";
String password = "yourPassword";
```

## Compiling the Project

Navigate to the project root and compile with:

```bash
javac -d bin src/main/java/carrinho/**/*.java
```

## Running the Project

To run the main menu interface:

```bash
java -cp bin carrinho.Program
```

To use the CLI:

```bash
java -cp bin carrinho.cli.SupermarketCLI --help
```

## Folder Structure

- `/src` - Contains source files
- `/bin` - Contains compiled `.class` files
- `/resources` - Contains database scripts and config
- `/entidades` - Contains entity classes like `Produto`
- `/servico` - Contains `Program.java`, the main entry point
- `/db` - Contains classes for DB connection and operations
- `/cli` - Contains the `SupermarketCLI.java` CLI interface  
