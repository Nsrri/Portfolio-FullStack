# Portfolio

## Portfolio-Backend 0.1.0

### Description

The Portfolio-Backend is the backbone of the Portfolio-Fullstack project which is implemented as a maven dynamic web application, it lets viewer of the portfolio to create an account for themself to let the developer team to generate a static based on geographic location and occupation to make a better user experience.



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


### Used Technologies


### Author




