<!-- ABOUT THE PROJECT -->
## Spring-LPD-REST

This is self learning and practicing project for Java Spring Boot. The main focus will be on REST API's.

Topics will be covered here:
* Project Initiate
* Handling Controllers, Mapping
* Handling Services, Repositories
* Postgresql, MySQL Database
* Handling JWT Token
* Handling API Headers



### Built With

* [Spring Boot](https://start.spring.io/)
* [Heroku](https://heroku.com/)

## Getting Started


### Prerequisites

To start coding you may need an IDE, alongside your pc need to be compatible with Java Environment.
* Install JDK (in this project Java 11 Used)
* Intellij/STS/Eclipse (in this project Intellij Ultimate Used)
* Install MySql Server
* GitHub Account
* Heroku Account

### Project Initiate

In this project I've created a new project from Spring Boot website. You may also create a new maven project from your IDE manually too.

1. Go to [https://start.spring.io/](https://start.spring.io/)
2. Select Maven Project
3. Select Java Langage
4. Select Spring Boot Version (used 2.7.8)
5. Provide Project Metadata: (used as below)<br>
   Group: com.imsisojib<br>
   Artifact: lpd<br>
   Name: portfolio<br>
   Package Name: com.imsisojib.lpd
   Packaging: Jar
   Java: 11
6. Add Following Dependencies: <br>
   Spring Web<br>
   Spring Security<br>
   Spring Data JPA<br>
   MySql Connector (used)<br>
   PostgreSQL Driver (used)<br>
7. Now Generate and Download the zip file.

## MySQL Setup
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
spring.datasource.url=jdbc:mysql://localhost:3306/db_name?useSSL=false
spring.datasource.username=root
spring.datasource.password=root_password
spring.jpa.show-sql=true

///Note: Below lines help to Create, Update, Delete table autometically
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto=update
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
drop table table_name; 
drop database db_name; 
```

## Annotations in Spring Boot

**@Controller**
```$xslt
The @Controller annotation indicates that a particular class serves the role of a controller. Spring Controller annotation is typically used in combination with annotated handler methods based on the @RequestMapping annotation. It can be applied to classes only. It's used to mark a class as a web request handler.
```

**@RestController**
```$xslt
Spring RestController annotation is a convenience annotation that is itself annotated with @Controller and @ResponseBody . This annotation is applied to a class to mark it as a request handler. Spring RestController annotation is used to create RESTful web services using Spring MVC.
```

**@Service**
```$xslt
It is used to mark the class as a service provider. So overall @Service annotation is used with classes that provide some business functionalities. Spring context will autodetect these classes when annotation-based configuration and classpath scanning is used.
```

**@Repository**
```$xslt
to annotate Classes as Repository
```

**@Entity**
```$xslt
to annotate Classes as Database Table
```

**@AutoWired**
```$xslt
to initialize any object that is mentioned as Service/Repository. It acutally handled by framework to initiaze specific Class object.
```

**@Configuration**
```$xslt
Spring @Configuration annotation is part of the spring core framework. Spring Configuration annotation indicates that the class has @Bean definition methods. So Spring container can process the class and generate Spring Beans to be used in the application.
```

**@Bean**
```$xslt
Spring @Bean Annotation is applied on a method to specify that it returns a bean to be managed by Spring context. Spring Bean annotation is usually declared in Configuration classes methods. In this case, bean methods may reference other @Bean methods in the same class by calling them directly.
```

**@Component**
```$xslt
@Component is an annotation that allows Spring to automatically detect our custom beans.
...
In other words, without having to write any explicit code, Spring will:
- Scan our application for classes annotated with @Component.
- Instantiate them and inject any specified dependencies into them.
- Inject them wherever needed.
```

## Dependency Usages and Explanation
**spring-boot-starter-validation**
```$xslt
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

This library provides validation annotations.
Example: 
import javax.validation.constraints.Size;

@Size(max = 30)
private String name; //maximum number of character can be used for this column
```

## Entity Relationship
### BEFORE DEEP DIVE
Let's see some use cases of difference relation helpers<br>

--> **What is 'CASCADE'?** <br>
CASCADE in SQL is used to simultaneously delete or update an entry from both the child and parent table. The keyword CASCADE is used as a conjunction while writing the query of ON DELETE or ON UPDATE.<br>
Example: 
```$xslt
CascadeType.ALL
//perform all types of actions
//constrained with Owner-Table.
//If you want to delete Reference-Table row then you must delete Owner-Table referenced row first.

CascadeType.REMOVE: When deleting an entity, it also deletes the entities held in this field.

CascadeType.REFRESH: When refreshing an entity, also refresh the entities held in this field.

CascadeType.MERGE: When merging entity state, also merge the entities held in this field.

```

--> **What is 'FetchType'?** <br>
```$xslt

FetchType.Lazy: Only fetches required fields/columns that has Getter Method

FetchType.Eager: Select all fields within an entity

```

### OneToOne Uni-Directional Relation
In this type of entity relation Owner-Table contains a ForeignKey column of Reference Table.
Example:
```$xslt

@Entity
class User{
   int id;
   String name;
   
   @OneToOne(cascade = CascadeType.ALL)
   Address address;
}

@Entity
class Address{
   int id;
   String info;
}

DONE. 
```

As there is no defined Column name for ForeignKey, so in User table a additional column will be added, name will be: [REFERENCE_TABLE_NAME+REFENCE_TABLE_PK_NAME]=> address_id. <br>
TO CHANGE/DEFINE CUSTOM COLUMN_NAME:
```$xslt@Entity
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(  name = "fk_address_id", referencedColumnName = "id")

DONE.
```

## REST API CALLING:
```$xslt@Entity

         //ADD DEPENDENCY
         <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		
         //IMPLEMENTATION
         String url = "api_url";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("Bearer token");

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                Map[].class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        var data = result.getBody();
```


## Add Swagger2

Add Dependency
```$xslt@Entity
        <dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
```

Add Swagger Config
```$xslt@Entity
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```

Update AndMatcher in Application.properties to retrieve all endpoints
```$xslt@Entity
#change spring to use antmatchers with configuring it in application.properties
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
```

Output: http://localhost:8080/swagger-ui.html#/

