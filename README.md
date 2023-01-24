#MySQL Setup
- Download MySql Server
- setup root and password

**Add mysql dependency:**
```$xslt
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
<version>8.0.32</version>
</dependency>
```

**Add DataSource config into application.properties:**

```$xslt
spring.datasource.url=jdbc:mysql://localhost:3306/lpd
spring.datasource.username=root
spring.datasource.password=rootX663
spring.jpa.show-sql=true
```

**MySQL database in terminal: (commands)**
```$xslt
mysql -u root -p //for connect mysql
```
```$xslt
show databases ; // to see all database
create database db_name ; // to create new database
use database_name ; // to use database
show tables ; // to show all tables within database
```