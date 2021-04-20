# desafioQuality

Endpoints:

------------------------------------------------------------------------------------------------

GET "/api/v1/hotels" (String dateFrom, String dateTo, String destination)

Returns the hotels that fulfill the given parameters.

Note: dateFrom and dateTo format must be "dd/MM/yyyy".

------------------------------------------------------------------------------------------------

POST "/api/v1/booking" (BookingHotelRequestDTO bookingRequest)

Reserves a hotel and returns a Status OK if it was a reserved, or a Status BAD REQUEST if it wasn't.

Note: BookingHotelRequestDTO must be a json with the following fields.

{
    "userName" : "",
    "booking" : {
        "dateFrom" : "",
        "dateTo" : "",
        "destination" : "",
        "hotelCode" : "",
        "peopleAmount" : "",
        "roomType" : "",
        "people" : [
            {
                "dni" : "",
                "name" : "",
                "lastname" : "",
                "birthDate" : "",
                "mail" : ""
            },
             {
                "dni" : "",
                "name" : "",
                "lastname" : "",
                "birthDate" : "",
                "mail" : ""
            }
        ],
        "paymentMethod" : {
            "type" : "",
            "number" : "",
            "dues" : ""
        }
    }
}

dateFrom and dateTo format must be "dd/MM/yyyy".
roomType can only be "Single", "Doble", "Triple" and "Múltiple" and must match the peopleAmount.
paymentMethod's type can only be "CREDIT" or "DEBIT".

------------------------------------------------------------------------------------------------

GET "/api/v1/flights" (String dateFrom, String dateTo, String origin, String destination)

Returns the flights that fulfill the given parameters.

Note: dateFrom and dateTo format must be "dd/MM/yyyy".

------------------------------------------------------------------------------------------------

POST "/api/v1/flight-reservation" (BookingFlightRequestDTO bookingRequest)

Reserves a flight and returns a Status OK if it was a reserved, or a Status BAD REQUEST if it wasn't.

Note: BookingFlightRequestDTO must be a json with the following fields.

{
    "userName" : "",
    "flightReservation" : {
        "dateFrom" : "",
        "dateTo" : "",
        "origin" : "",
        "destination" : "",
        "flightNumber" : "",
        "seats" : "",
        "seatType" : "",
        "people" : [
            {
                "dni" : "",
                "name" : "",
                "lastname" : "",
                "birthDate" : "",
                "mail" : ""
            },
             {
                "dni" : "",
                "name" : "",
                "lastname" : "",
                "birthDate" : "",
                "mail" : ""
            }
        ],
        "paymentMethod" : {
            "type" : "",
            "number" : "",
            "dues" : ""
        }
    }
}

dateFrom and dateTo format must be "dd/MM/yyyy".
seatType can only be "Economy" and "Business".
paymentMethod's type can only be "CREDIT" or "DEBIT".

------------------------------------------------------------------------------------------------