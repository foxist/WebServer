# WebServer
Task from DINO Systems
1) Let imagine that we have system that holds and manages contact directory of many companies.

There is one of HTTP endpoints that responsible for retrieving user data by name:

GET /company/%companyId%/users?name=%someName%

Please write test scenarios for this endpoint, as many as you can.

Example: Request some_domain.com/company/777/users?name=Izergil where 777 is ID of company that does not exist, check that error is returned and HTTP status code is 404


How is it work?

My example: http://localhost:8080/WS/some_domain.com/company/1234/users?name=Ruslan

When you run the programm, you will see a browser page with a http://localhost:8080/WS/

Then you can register any domain otherwise the program will give an error. Example: http://localhost:8080/WS/some_domain.com/

Then you must fill out a request for a pattern. Pattern: /company/company_id/users?name=author

Company_id - you can see in the company_id.txt file, if you enter the id from this file, an error will pop up, since you cannot use the data from the ID.
On the contrary, if you enter the user that is in the file name.txt, then it's cool, otherwise the error.
