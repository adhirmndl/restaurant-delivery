# restaurant-delivery
select shortest path between multiple restaurants and delivery customer locations, also consider meal prep. time.


Build commands
```
mvn compiler:compile
mvn clean install
// to run on cli
java -Dfile.encoding=UTF-8 -classpath target\classes; org.assignment.MainApplication
```
# Output 1
```
---------------------------- [ Start Application ] ------------------------------------
Avg speed: 20 KM/Hr
R0 => Restaurant 0 , R1 => Restaurant 1 , R2 => Restaurant 2 , C0 => Customer 0, C1 => Customer 1 , C2 => Customer 2
Restaurant Coordinates: 12.941044, 77.621111 | Restaurant avg meal preparation time : 00 hr 30 Min 11 Sec
Restaurant Coordinates: 12.957575, 77.598489 | Restaurant avg meal preparation time : 00 hr 25 Min 43 Sec
Customer Coordinates: 12.957575, 77.598489
Customer Coordinates: 12.967260, 77.582017
---------------------------- [ Solution ] ------------------------------------
Visiting order: Start  --------------> R0 --------------> C0 --------------> R1 --------------> C1
Total time taken: 00 hr 45 Min 37 Sec
```
# Output 2

```
---------------------------- [ Start Application ] ------------------------------------
Avg speed: 20 KM/Hr
R0 => Restaurant 0 , R1 => Restaurant 1 , R2 => Restaurant 2 , C0 => Customer 0, C1 => Customer 1 , C2 => Customer 2
Restaurant Coordinates: 12.941044, 77.621111 | Restaurant avg meal preparation time : 00 hr 43 Min 17 Sec
Restaurant Coordinates: 12.935974, 77.621561 | Restaurant avg meal preparation time : 00 hr 45 Min 09 Sec
Customer Coordinates: 12.934075679250189, 77.61938777976583
Customer Coordinates: 12.942092, 77.615033
---------------------------- [ Solution ] ------------------------------------
Visiting order: Start  --------------> R0 --------------> R1 --------------> C0 --------------> C1
Total time taken: 00 hr 49 Min 07 Sec
```
# Output 3

```
---------------------------- [ Start Application ] ------------------------------------
Avg speed: 20 KM/Hr
R0 => Restaurant 0 , R1 => Restaurant 1 , R2 => Restaurant 2 , C0 => Customer 0, C1 => Customer 1 , C2 => Customer 2
Restaurant Coordinates: 12.973291, 77.547089 | Restaurant avg meal preparation time : 00 hr 45 Min 46 Sec
Restaurant Coordinates: 12.935315765015698, 77.61285796970836 | Restaurant avg meal preparation time : 00 hr 36 Min 15 Sec
Customer Coordinates: 12.935974, 77.621561
Customer Coordinates: 12.942092, 77.615033
---------------------------- [ Solution ] ------------------------------------
Visiting order: Start  --------------> R1 --------------> C1 --------------> R0 --------------> C0
Total time taken: 01 hr 12 Min 59 Sec
```
