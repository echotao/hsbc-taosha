## User Guide

### create account
curl --location --request POST 'http://localhost:8080/account' \
--data ''

### deposit
curl --location --request POST 'http://localhost:8080/account/0108413807670341/depositAction?amount=100' \
--data ''

### withdraw
curl --location --request POST 'http://localhost:8080/account/0108413807670341/withdrawAction?amount=100' \
--data ''
