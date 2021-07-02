#Product Reactive crud Spring RESTAPI
```
App provides basic CRUD operations connecting to MySql database using reactive relational database driver (R2DBC)
```

##How to run the project 
```
clone the git project
cd in the direcctory where you want to clone

git clone https://github.com/AndyG911/product-rest-api-webFlux.git
```
##Set up the Database MySQL Update the application.properties
```
spring.r2dbc.url=r2dbc:pool:mysql://<<Your MySQL Host>>:3306/customer
spring.r2dbc.username=<<User Name>>
spring.r2dbc.password=<<Password>>

```
##create table in the MySQL schema
```
CREATE TABLE product(id SERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL, qty INTEGER(100) NOT NULL,price DECIMAL NOT NULL);
```
##Start the application
```
./mvnw spring-boot:run
```
##Api's sample request
###Create a record

```
curl --header "Content-Type: application/json" \
      --request POST
      -- data{\"name\":\"levis jeans\",\"qty\":10,\"price\":790}
      http://localhost:8050/product/products
```
###Get all products:
```
curl --header "Content-Type: application/json" \
       --request GET
       --  data :  {
                "id": "1",
                "name": "mobile",
                "qty": 10,
                "price": 1500
                },
    http://localhost:8050/product
```
###Get a record:
```
http://localhost:8050/product/1
```
###Update a record:
```
curl   --header "Content-Type: application/json" \
       --request PUT
       --data "{\"id\":\"2\",\"name\":\"Oven\",\"qty\":230,\"price\":1450}"
       http://localhost:8050/product/update/2
```
###Delete a record:
```
http://localhost:8050/product/delete/2
```