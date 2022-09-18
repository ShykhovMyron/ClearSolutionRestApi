**Java practical test assignment ![](Aspose.Words.9d907308-5a39-4f69-af85-88572e135c73.001.jpeg)**

You need to create a RESTful API based on the web Spring Boot application: controller, responsible for the resource named **Users**.

1. It has the following fields:
   1. Email (required). Add validation against email pattern
   1. First name (required)
   1. Last name (required)
   1. Birth date (required). Value must be earlier than current date
   1. Address (optional)
   1. Phone number (optional)
1. It has the following functionality:
   1. Create user. It allows to register users who are more than [18] years old. The value [18] should be taken from properties file.
   1. Edit user
   1. Replace user
   1. Delete user
   1. Search for users by birth date range. Add the validation which checks that “From” is less than “To”.  Should return a list of objects
1. Code is covered by unit tests using Spring
1. Code has exception handlers
1. Service responsible for business logic can be an interface, no need to implement it
1. Use of database is not necessary
1. Latest version of Spring Boot. Java version of your choice
1. You can use Spring Initializer utility to create the project:Spring Initia[lizr](https://start.spring.io)
1. List of resources that will be helpful:
- [RESTful API Design. Best Practices in a Nutshell.](https://phauer.com/2015/restful-api-design-best-practices/)
- [Error Handling for REST with Spring | Baeldung](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Testing in Spring Boot | Baeldung](https://www.baeldung.com/spring-boot-testing#unit-testing-with-webmvctest)
- [Testing | Spring](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-server)
