# A marketplace api 
## Project
A marketplace REST API in spring-boot.  
Project made to learn about spring-boot, REST API, MVC model, and databases.

## Parts of the project
### Announces 
A list of announces made in the market place, have values name, description, type, seller and value.
You can search all, search by id, add, delete and modify announces.

### Seller
To be implement.

### Type
To be implement.

## How To use
### Start application
Start spring-boot in the file of the program
```
mvn spring-boot:run
```

### URL 
#### search all
get method
```
http://localhost:8080/announces
```

#### search by id
get method
```
http://localhost:8080/announces/{id}
```

#### add announce
post method
must have a body with the announce infos
```
http://localhost:8080/announces/add
```

#### remove announce
delete method
```
http://localhost:8080/announces/{id}
```

#### update announce
put method
must have a body with the announce infos
```
http://localhost:8080/announces/{id}
```

#### search by type
get method
```
http://localhost:8080/announces/type?type={type}
```

#### show all types
get method
```
http://localhost:8080/announces/types
```

#### search by seller
get method
```
http://localhost:8080/announces/seller?seller={seller}
```

#### show all sellers
get method
```
http://localhost:8080/announces/sellers
```
