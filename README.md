# Portfolio-FullStack

## Portfolio-Backend 0.1.0

### Description

The Portfolio-Backend is the backbone of the Portfolio-Fullstack project which is implemented as a maven dynamic web application, The Http requests are implemented with the help of Spring-Jersey library and The server can be run on Apache Tomcat(10.1). The required JDK for this project is >= JDK 20 and the API is documented via OpenAPI.
The aim of this project is to let viewer of the portfolio to create an account for themselves to let the developer team to generate a static based on geographic location and occupation to make a better user experience.



### ERM Diagram

ERM Stands for 'Entity Relational Model'

| ERM Diagram | 
| ----------- |
| <img width="531" alt="Screenshot 2023-12-20 at 08 46 21" src="https://github.com/Nsrri/Portfolio-FullStack/assets/90839245/244c634e-56b6-4e8a-a3c7-6980388a02ec">|


### Validation rules
| Email 📧      | Password 🔑 | Birthdate 📆 | occupation name |
| ----------- | ----------- |----------- |----------- |
| username@mailserver.domainname | min 8 chars, starts with uppercase char, numbers, special chars  | between 1200 and Date.now() | not null and not shorter than 3 |




### Valid Users for test


🔵 Just his/her own data

**Viewer**
| Rights     | Admin | Viewer | 
| ----------- | ----------- |----------- |
| CREATE | 🟢  | 🔵 | 
| READ | 🟢 | 🔵 | 
| UPDATE | 🟢 | 🔵 | 
| DELETE | 🟢 | 🔴| 
| READ ALL | 🟢 | 🔴| 
| READ OCCUPATION | 🟢 | 🔴| 


**Occupation**
| Rights     | Admin | Viewer | 
| ----------- | ----------- |----------- |
| CREATE | 🟢  | 🔴 | 
| READ | 🟢 | 🔴 | 
| UPDATE | 🟢 | 🔴 | 
| DELETE | 🟢 | 🔴| 
| READ ALL | 🟢 | 🔴| 
| READ VIEWER| 🟢 | 🔴| 


### Last Word

This Project was a learn project but it will be used as the backend for portfolio websit which I want to use it as my portfolio and  add my github repositories then share it in my socila media. There will be another version (1.0.0) of Portfolio-Backend soon, it will contain huge changes such as better database connection and better security.


### Used Technologies

[Jersey]
(https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/howto-jersey.html)

[Apache tomcat]
(https://tomcat.apache.org/)https://tomcat.apache.org/)

[Mysql]
(https://tomcat.apache.org/)https://tomcat.apache.org/](https://www.mysql.com/)


### Author
Nasrin Jafari




