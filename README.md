## ****Applause Coding Assignment**** 

To run application:
* Install Docker :simple_smile:
* Build app: `mvn clean install`
* Run: `docker-compose -f docker-compose.yml up --build` from **/docker** directory
* Please, make sure, that Docker has created DB and schemas. 
If it did not, please create them manually via script `./init.sql` from **/docker/postgres/initdb** directory  
* Run app: `java -jar applause-coding-assignment-1.0-SNAPSHOT.jar` from **/target** directory

The swagger will be available on **http://localhost:5643/swagger-ui.html** or you can check it with [Applause-Coding-assignment-front]() :simple_smile: 

