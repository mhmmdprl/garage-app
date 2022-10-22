# garage-app

#Curl examples : 
- curl --location --request POST 'http://localhost:8080/garage/park' \
--header 'Content-Type: application/json' \
--data-raw '{
    "plate":"01 PRL 34",
    "color" : "Blue",
    "vehicleSize": "CAR"
}'


- curl --location --request PUT 'http://localhost:8080/garage/leave?id=01 PRL 34'


- curl --location --request GET 'http://localhost:8080/garage/status'
garage-app
