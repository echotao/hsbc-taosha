The root cause of the 404 errors was the absence of the @RestController annotation for MicroserviceApplication. Now it has been fixed.

I also update the withdraw and deposit request body from type "double" to "DepositRequest" and "WithdrawRequest" as required by the framework, the request body must be a object, not a primitive data type.
