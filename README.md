user.status=ON
![image](https://github.com/Ruslana3012/Andersen-Course/assets/105870200/ced9027a-95c3-4474-80a6-5271074dae40)
user.status=OFF
![image](https://github.com/Ruslana3012/Andersen-Course/assets/105870200/f3266e86-6614-4b6b-aa5a-36e2a8b1a99e)

1. Add two interconnected objects in your application with one-to-many relation (like User-Ticket - i.e. one 
user may have several Tickets)
2. Make sure that in your DB there are two tables - User and Ticket.
3. Add a function to your application’s DAO layer that updates User entity and creates Ticket entity in the 
same method (like change user status to ACTIVATED and create a new Ticket)
4. Add transactional support to this method using Spring Core functionality.
5. Add DB properties that you use to create a datasource for your DB to application.properties file.
6. Add a property to application.properties file to turn on/off an ability to update User and create ticket 
(throw an exception if the property is OFF).
7. Add a file in the resource folder of your Spring service. Create a method and use Spring Resource
interface to load the content of the tickets JSON file from Lesson 5 in your application memory - create 
an array list out of the file content.

