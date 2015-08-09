Restack is just very a simple rest stack it combines:
* Jersey
* Jackson
* JPA (Eclipse link)
* H2
* JUnit

To run tests do: 
   mvn test

To start try the Rest endpoint:
   mvm clean jetty:run

and then:
   curl http://localhost:8080/world