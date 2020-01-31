
Part 1:
Day 1 involved setting up for the semester. I am not quite certain how to prove that I have successfully managed this except for the fact
that I was able to complete the excercises for the proceeding days.

Day 2: involved two assignments; "Fundamental JPA and Facades Guideline" & "Fundamental JPA and Facades Guideline"
The first of which you can probably skip seeing as it does not contain anything unique to this week and is not mentioned in the friday hand-in.
Nevertheless I have uploaded it as
- "01-28PlainJPA" just to be sure.
The other assignment is split in two projects by the names:
- "01-28Customer"
- "01-28Point"
These projects serve to show my basic understanding of how JPA works as well as my ability to use the 'EntityManager' tools.
In order to run these Java projects, a local MySQL DB is required to have been set up beforehand.

Day 3: introduces REST and JSON and how to use endpoints in a web application. The assignments linked to the Excercise titled
"REST-intro-day3-exercises" has been dubbed
- "01-29Rest1"
- "01-29Customer_withRest"
For the first assignment use this path to verify it is working (after running the project).
http://localhost:8080/01-29rest1/api/animal/random
For the second assignment, seeing as it is a continuation of Day2, again, you will need a local MySQL DB with the proper name and connection
setup to make it work.

Day 4: 
- "01-30Employee"


In order to answer the question posed in the assignment for wednesday (Day-3-REST-intro) I will write a brief observation here:
18. Reflect on every step you took. You will need to do this many times this semester
	a. Summarize (in writing?) the crucial steps for making this work.

Aside from making sure you are running a web application project and not a Java application in Netbeans, having a working Tomcat installed on
your local PC, what I would surmize to be the most important step to get it to work would be remembering to add the necessary dependencies to
said web application project:
'org.glassfish.jersey.bundles'
Furthermore it is also a good idea to remember to add the dependencies:
'mysql-connector-java' for working with a MySQL Database
'com.mysql.cj.jdbc.Driver' for working with entity classes
'com.google.code.gson:gson' for working with gson