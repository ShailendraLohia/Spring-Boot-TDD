# Spring-Boot-TDD

# When to use Http Status 204 and 205
https://benramsey.com/blog/2008/05/http-status-204-no-content-and-205-reset-content/

# Login to H2 database
https://medium.com/@harittweets/how-to-connect-to-h2-database-during-development-testing-using-spring-boot-44bbb287570

# Data Validations

1. User Input data: Validate at controller Level
2. Business Data: Validate at service Level

https://stackoverflow.com/questions/21407840/validation-in-spring-mvc-in-controllers-or-service-layer

# Test Cases Scenario
<b> POST API </b> 
<br></br>

1. Happy Scenario Test
2. Data Validation Test
3. RequestBody is Empty


<b> GET API </b>
<br></br>

1. Happy Scenario Test
2. Path Variable pass as null
3. Invalid Path Variable like constraint violation
4. Not Found Exception (can be thrown in Service Layer)
