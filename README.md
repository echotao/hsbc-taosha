To Mark:
The root cause of the 404 errors was the absence of the @RestController annotation for MicroserviceApplication. Now it has been fixed.
I also update the withdraw and deposit request body from type "double" to "DepositRequest" and "WithdrawRequest" as required by the framework, the request body must be a object, not a primitive data type.

## User Guide

### create account
curl --location 'http://localhost:8080/create' \
--header 'Content-Type: application/json' \
--data '{"amount":100}'

### deposit
curl --location 'http://localhost:8080/deposit/ACC1?amount=100.00' \
--header 'Content-Type: application/json' \
--data '{"amount":100}'

### withdraw
curl --location 'http://localhost:8080/withdraw/ACC1?amount=100.00' \
--header 'Content-Type: application/json' \
--data '{"amount":100}'