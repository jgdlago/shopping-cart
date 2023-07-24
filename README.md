# shoppingCart
Back-end:
Deve existir um banco de dados postgres com as seguintes configurações:

spring.datasource.url=jdbc:postgresql://localhost:5432/soluct_shoppingcart_db
spring.datasource.username=postgres
spring.datasource.password=admin

Front-end:

Acessar a pasta shopping-cart pelo terminal e executar o comando npm start ou yarn start

POST http://localhost:8080/produto
GET http://localhost:8080/produto
PUT http://localhost:8080/produto/{idProduto}/addCarrinho/{idCarrinho}
GET http://localhost:8080/carrinho/update
DLETE http://localhost:8080/produto/codigo/{codigoProduto}
PUT http://localhost:8080/produto/{idProduto}/updateQtd/{quantidade}
