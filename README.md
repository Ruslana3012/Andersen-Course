Lesson5: 
![image](https://github.com/Ruslana3012/Andersen-Course/assets/105870200/7614bcfb-3d40-41c5-9836-27c0ae51faba)
Users have started to issue hand-made bus tickets, your system should be ready to validate each ticket and show a 
warning in case of a malicious input.
Create a function to validate Bus ticket. Show an error in the console if 1+ validation rule is violated.
The output should be the following:
…
Total = {number of tickets in total}
Valid = {number of valid tickets}
Most popular violation = {“start date” or “price” or “ticket type”}
…
Rules:
Only DAY, WEEK and YEAR types must have a [start date].
Price can’t be zero.

Add additional abilities to it:
1) An ability to read tickets from a file.
2) New validator - [start date] can’t be in the future.
3) New validator - [ticket type] valid values are DAY, WEEK, MONTH, YEAR.
4) New validator - [price] should always be even.
   
{“ticketClass":"CLA","ticketType":"DAY","startDate":"2025-01-01","price":0}
{"ticketClass":"CLA", "ticketType":"DAY", "startDate":"2025-01-01", "price":10}
{"ticketClass":"CLA", "ticketType":"PRIME", "startDate": null, "price":1000}
{"ticketClass":"STD", "ticketType":"DAY", "startDate":"2025-01-01", "price":0}
{"ticketClass":"STD", "ticketType":"WEEK", "startDate":"2020-01-01", "price":50}
{"ticketClass":"CLA", "ticketType":"YEAR", "startDate":"2020-01-01", "price":500}
{"ticketClass":"CLA", "ticketType":"MONTH", "startDate":"2020-01-01", “price":100}
{"ticketClass":"CLA", "ticketType":"DAY", "startDate":"2020-01-01", “price":100}
{“ticketClass":null, "ticketType":"MONTH", "startDate":"2020-01-01", “price":100}
{"ticketClass":"STD", "ticketType":"MONTH", "startDate":"2020-01-01", “price":100}
{"ticketClass":"STD", "ticketType":null, "startDate":"2020-01-01", “price":1000}
{"ticketClass":"STD", "ticketType":"YEAR", "startDate":"", “price":100}
{"ticketClass":"CLA", "ticketType":"MONTH", "startDate":"2020-01-01", “price":99}
{"ticketClass":"STD", "ticketType":"PRIME", "startDate":"2020-01-01", “price":0}
{"ticketClass":"CLA", "ticketType":"YEAR", "startDate":null, “price":100}
{"ticketClass":"STD", "ticketType":"DAY", “startDate":"2028-01-01", “price":100}
{"ticketClass":"CLA", "ticketType":"DAY", "startDate":null, "price":null}
