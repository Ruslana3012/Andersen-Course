![image](https://github.com/user-attachments/assets/26fe246f-ae1a-47d4-ae2f-a6918d606a23)
![image](https://github.com/user-attachments/assets/ef04269a-cc11-48fa-9590-4b5b5d39d0f6)

1. Convert your project to Spring Boot project. Allow the framework to create DB data source for you.
2. Create your custom @Configuration with a @Bean called ThisIsMyFirstConditionalBean. The bean 
should appear in your application only if its @ConditionalOnProperty statement is true.
3. Add Spring Data support to your project. Create/replace your custom Ticket CRUD DAO class with the 
@Repository that extends Spring Data CRUD repository.
4. Make your Ticket application a web service using Spring MVC with only one @RestController, that has a 
REST endpoint that returns a JSON with a single ticket.
5. Add Spring Security Support to your project and make your single endpoint protected with username + 
password protection.

