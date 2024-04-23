## Travel Boooking Service

This application will help in booking travel tickets

### Pre-requsites
* Java 17

### Operations Covered

#### Operations for valid or anonymous user

* GET /stations => Fetch all station name and codes
```shell
> curl -X GET --location "http://localhost:8080/stations"

[
   {
      "code" : "LON",
      "name" : "LONDON"
   },
   {
      "code" : "ASH",
      "name" : "ASHFORD"
   },
   {
      "code" : "FOL",
      "name" : "FOLKSTONE"
   },
   {
      "code" : "CAL",
      "name" : "CALAIS"
   },
   {
      "code" : "PAR",
      "name" : "PARIS"
   },
   {
      "code" : "ORL",
      "name" : "ORLEANS"
   },
   {
      "code" : "FRA",
      "name" : "FRANCE"
   }
]
```
* GET /stations/{STATION_CODE} => Fetch station using station code
```shell
> curl -X GET --location "http://localhost:8080/stations/ASH"

{"name":"ASHFORD","code":"ASH"}
```
* GET /trains => Fetch all trains
```shell
> curl -X GET --location "http://localhost:8080/trains"

[
   {
      "arrivalTime" : "09:00:00",
      "departureTime" : "06:30:00",
      "destination" : {
         "code" : "PAR",
         "name" : "PARIS"
      },
      "name" : "PARIS EXPRESS",
      "number" : 60001,
      "source" : {
         "code" : "LON",
         "name" : "LONDON"
      }
   },
   {
      "arrivalTime" : "12:00:00",
      "departureTime" : "09:30:00",
      "destination" : {
         "code" : "PAR",
         "name" : "PARIS"
      },
      "name" : "PARIS EXPRESS",
      "number" : 60002,
      "source" : {
         "code" : "LON",
         "name" : "LONDON"
      }
   },
   {
      "arrivalTime" : "15:00:00",
      "departureTime" : "12:30:00",
      "destination" : {
         "code" : "PAR",
         "name" : "PARIS"
      },
      "name" : "PARIS SUPERFAST",
      "number" : 60003,
      "source" : {
         "code" : "LON",
         "name" : "LONDON"
      }
   },
   {
      "arrivalTime" : "18:00:00",
      "departureTime" : "15:30:00",
      "destination" : {
         "code" : "PAR",
         "name" : "PARIS"
      },
      "name" : "PARIS SUPERFAST",
      "number" : 60004,
      "source" : {
         "code" : "LON",
         "name" : "LONDON"
      }
   },
   {
      "arrivalTime" : "11:00:00",
      "departureTime" : "07:00:00",
      "destination" : {
         "code" : "FRA",
         "name" : "FRANCE"
      },
      "name" : "FRANCE SUPERFAST",
      "number" : 60005,
      "source" : {
         "code" : "LON",
         "name" : "LONDON"
      }
   },
   {
      "arrivalTime" : "15:00:00",
      "departureTime" : "11:00:00",
      "destination" : {
         "code" : "FRA",
         "name" : "FRANCE"
      },
      "name" : "FRANCE EXPRESS",
      "number" : 60006,
      "source" : {
         "code" : "LON",
         "name" : "LONDON"
      }
   },
   {
      "arrivalTime" : "19:00:00",
      "departureTime" : "15:00:00",
      "destination" : {
         "code" : "FRA",
         "name" : "FRANCE"
      },
      "name" : "FRANCE SUPERFAST",
      "number" : 60007,
      "source" : {
         "code" : "LON",
         "name" : "LONDON"
      }
   }
]
```
* GET /trains => Fetch train by train number
```shell
> curl --location "http://localhost:8080/trains/{TRAIN_NUMBER}"

{
   "arrivalTime" : "19:00:00",
   "departureTime" : "15:00:00",
   "destination" : {
      "code" : "FRA",
      "name" : "FRANCE"
   },
   "name" : "FRANCE SUPERFAST",
   "number" : 60007,
   "source" : {
      "code" : "LON",
      "name" : "LONDON"
   }
}
```
* POST /tickets => Book travel tickets train by train number
```shell
> curl -X POST --location "http://localhost:8080/tickets" \
--header "Content-Type: application/json" \
--header "Authorization: Basic dmlqYXlhcmFnaGF2YW4xODA1QGdtYWlsLmNvbTpWcmFnMTIzIw==" \
--data "{
    \"travelInfo\": {
        \"train\": {
            \"number\": 60002
        },
	    \"travelDate\": \"2024-04-22\",
	    \"from\": {
            \"code\": \"LON\"
        },
	    \"to\": {
            \"code\": \"PAR\"
        }
    },
	\"passengersInfo\": [
        {
            \"firstName\": \"rajalakshmi\",
	        \"lastName\": \"n\",
	        \"age\": 39
        },
        {
            \"firstName\": \"theekshika\",
	        \"lastName\": \"v\",
	        \"age\": 12
        }
    ]
}"

{
   "passengersBookingInfo" : [
      {
         "age" : 12,
         "coachNumber" : 1003,
         "firstName" : "theekshika",
         "lastName" : "v",
         "passengerId" : 3,
         "seatNumber" : 4,
         "seatStatus" : "SEAT_CONFIRMED",
         "travelStatus" : "PENDING_TRAVEL"
      },
      {
         "age" : 39,
         "coachNumber" : 1003,
         "firstName" : "rajalakshmi",
         "lastName" : "n",
         "passengerId" : 4,
         "seatNumber" : 3,
         "seatStatus" : "SEAT_CONFIRMED",
         "travelStatus" : "PENDING_TRAVEL"
      }
   ],
   "travelBookingInfo" : {
      "bookingDate" : "2024-04-22T13:17:48.507357",
      "bookingId" : 500002,
      "bookingStatus" : "BOOKING_CONFIRMED",
      "from" : {
         "code" : "LON",
         "name" : "LONDON"
      },
      "to" : {
         "code" : "PAR",
         "name" : "PARIS"
      },
      "train" : {
         "arrivalTime" : "12:00:00",
         "departureTime" : "09:30:00",
         "destination" : {
            "code" : "PAR",
            "name" : "PARIS"
         },
         "name" : "PARIS EXPRESS",
         "number" : 60002,
         "source" : {
            "code" : "LON",
            "name" : "LONDON"
         }
      },
      "travelDate" : "2024-04-22",
      "travelFare" : 10,
      "travelStatue" : "PENDING_TRAVEL"
   }
}
```
* Get /tickets => Get all booked tickets for user
```shell
> curl -X GET --location "http://localhost:8080/tickets"

[
    {
        "travelBookingInfo": {
            "train": {
                "number": 60002,
                "name": "PARIS EXPRESS",
                "source": {
                    "name": "LONDON",
                    "code": "LON"
                },
                "destination": {
                    "name": "PARIS",
                    "code": "PAR"
                },
                "departureTime": "09:30:00",
                "arrivalTime": "12:00:00"
            },
            "travelDate": "2024-04-22",
            "from": {
                "name": "LONDON",
                "code": "LON"
            },
            "to": {
                "name": "PARIS",
                "code": "PAR"
            },
            "bookingId": 500001,
            "bookingDate": "2024-04-22T13:17:29.651256",
            "bookingStatus": "BOOKING_CONFIRMED",
            "travelStatue": "PENDING_TRAVEL",
            "travelFare": 10
        },
        "passengersBookingInfo": [
            {
                "firstName": "rajalakshmi",
                "lastName": "n",
                "age": 39,
                "passengerId": 1,
                "coachNumber": 1003,
                "seatNumber": 1,
                "travelStatus": "PENDING_TRAVEL",
                "seatStatus": "SEAT_CONFIRMED"
            },
            {
                "firstName": "theekshika",
                "lastName": "v",
                "age": 12,
                "passengerId": 2,
                "coachNumber": 1003,
                "seatNumber": 2,
                "travelStatus": "PENDING_TRAVEL",
                "seatStatus": "SEAT_CONFIRMED"
            }
        ]
    },
    {
        "travelBookingInfo": {
            "train": {
                "number": 60002,
                "name": "PARIS EXPRESS",
                "source": {
                    "name": "LONDON",
                    "code": "LON"
                },
                "destination": {
                    "name": "PARIS",
                    "code": "PAR"
                },
                "departureTime": "09:30:00",
                "arrivalTime": "12:00:00"
            },
            "travelDate": "2024-04-22",
            "from": {
                "name": "LONDON",
                "code": "LON"
            },
            "to": {
                "name": "PARIS",
                "code": "PAR"
            },
            "bookingId": 500002,
            "bookingDate": "2024-04-22T13:17:48.507357",
            "bookingStatus": "BOOKING_CONFIRMED",
            "travelStatue": "PENDING_TRAVEL",
            "travelFare": 10
        },
        "passengersBookingInfo": [
            {
                "firstName": "rajalakshmi",
                "lastName": "n",
                "age": 39,
                "passengerId": 4,
                "coachNumber": 1003,
                "seatNumber": 3,
                "travelStatus": "PENDING_TRAVEL",
                "seatStatus": "SEAT_CONFIRMED"
            },
            {
                "firstName": "theekshika",
                "lastName": "v",
                "age": 12,
                "passengerId": 3,
                "coachNumber": 1003,
                "seatNumber": 4,
                "travelStatus": "PENDING_TRAVEL",
                "seatStatus": "SEAT_CONFIRMED"
            }
        ]
    }
]
```
* PUT /tickets => Update seat for a booking
```shell
> curl --X PUT --location "http://localhost:8080/tickets" \
--header "Content-Type: application/json" \
--header "Authorization: Basic dmlqYXlhcmFnaGF2YW4xODA1QGdtYWlsLmNvbTpWcmFnMTIzIw==" \
--data "{
    \"bookingId\": \"500002\",
    \"passengerSeat\": {
        \"3\": {
            \"coachNumber\": 1003,
            \"seatNumber\": 13
        }
    }
}


"

```