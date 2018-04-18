## Setup

  Ensure you have MySQL Available:

  ```shell
    brew install mysql
    brew services start mysql
  ```

  Since i'm too lazy to set up Flyway, connect to mysql:

  ```shell
    mysql -uroot
  ```

  Then create and "seed" the db:
  ```sql
  CREATE DATABASE development_database;
  USE development_database;
  CREATE TABLE user(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(255), 
    last_name varchar(255)
    );
  insert into user(first_name, last_name) values('Alex', 'Hall');
  ```

  Clone the project, then run:
  ```shell
    mvn clean install && mvn test
  ```
 