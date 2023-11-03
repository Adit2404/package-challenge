
#### Assignment: Package Challenge

#### Prerequisite:
- Java JDK 17
- Multithreading - optimistic locking
- Spring boot
  
#### Testing
The application is made with the spring of TDD, and that is the reason it contains a lot of unit tests. 

#### Technologies
- Java 17
- Spring boot
- Spring AOP
- Junit5
- Openpojo
- Apache commons
- Lombok
- Builder Pattern
- SOLID

#### Algorithm Reason

The code ensures safety across threads by using Java 1.8's StampedLock, with an optimistic read lock that minimizes synchronization overhead. To solve the problem, rather than more complex methods like dynamic programming, I've opted for a faster, lightweight approach. The solution combines a PriorityQueue (Heap) and a Map to manage and sort items according to the Knapsack algorithm. This is encapsulated within a class called "KnapSack," which handles product collection and sorting while following Knapsack algorithm principles
#### Built With
- Java
- Spring Boot
- Maven
- Clean And Build
- mvn clean install

#### Build And Test
- mvn clean test
