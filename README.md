# money-transfer

Application to transfer money between two accounts

# How to run

To build the project you need have the maven installed, then access the project root directory
 
By default, the application is available at `http://localhost:8080/`
 
 # Technologies Used
 
 1. Java as programming language
 2. Libraries:
 
    - SparkJava - micro framework for creating web applications
    - Jdbi - a lightweight framework for DB abstraction
    - Unirest - a thin HTTP library used for testing purpose
    - Junit - testing framework for Java
    - Gson - library to convert Java Objects into JSON and back
 3. Maven as software project management and comprehension tool.
 
 # Api
 
 Methods
 
 | Method | URl | Query Param | Description |
 |--|--|--|--|
 | GET | api/v1/accounts | None | Return all accounts |
 | GET | api/v1/accounts/{id} | None | Return account by id |
 | POST | api/v1/accounts | None | Create a new account |
 | GET | api/v1/accounts/{id}/transferences | f -> t: Transferences received f -> anything: Transferences sended |Return the transferences by account id |
 | GET | api/v1/transferences | None | Return all transferences |
 | POST | api/v1/transferences | None | Create a new transference |
 
 # Example
 
   **Request**
 
    GET /accounts/1000
    
  **Body**
  
    None
  
  **Response Successfully**
  
    HTTP_CODE : 200
  
    {
      "id":1000,
      "owner": "Carlos",
      "balance":2000.21,
      "createDate":"Dec 11, 2019 10:12:12 AM"
    }

  **Request**

    POST /accounts
  
  **Body**
  
    {
      "owner":"Carlos",
      "balance": 13
    }
    
  **Response Successfully**
  
    HTTP_CODE : 201
  
    {
      "id":1003,
      "owner":"Carlos",
      "balance": 13
    }
    
  **Request**
    
    POST /api/v1/transferences
    
  **Body**
  
    {
      "fromAccount" : 1000,
      "toAccount" : 1001,
      "amount" : 30
    }
    
  **Response Successfully**
  
    HTTP_CODE : 201
  
    {
        "id":1004,
        "fromAccount":1000,
        "toAccount":1001,
        "amount":30
    }
  
  **Request**
  
    GET api/v1/transferences
    
  **Body**
  
    None
  
  **Response Successfully**
  
      HTTP_CODE : 200
  
    [
      {
      "id":1000,
      "fromAccount":1001,
      "toAccount":1002,
      "amount":30.35,
      "createDate":"Dec 11, 2019 10:12:12 AM"
      },
      {
      "id":1001,
      "fromAccount":1002,
      "toAccount":1001,
      "amount":70.00,
      "createDate":"Dec 11, 2019 10:12:12 AM"},
      {
      "id":1002,
      "fromAccount":1002,
      "toAccount":1001,
      "amount":65.00,
      "createDate":"Dec 11, 2019 10:12:12 AM" 
      }
    ]
    
 **Request**
    
    GET /api/v1/accounts/1002/transferences?f=t
    
  **Body**
 
    None
  
  **Response Successfully**
  
    HTTP_CODE : 200
  
    [
      {
      "id":1002,
      "fromAccount":1002,
      "toAccount":1001,
      "amount":65.00,
      "createDate":"Dec 11, 2019 10:12:12 AM"
      },
      {
      "id":1003,
      "fromAccount":1002,
      "toAccount":1001,"amount":55.00,
      "createDate":"Dec 11, 2019 10:12:12 AM"},
      {"id":1004,
      "fromAccount":1000,
      "toAccount":1001,
      "amount":30.00,
      "createDate":"Dec 9, 2019 10:17:49 AM"
      }
    ]
    
      
