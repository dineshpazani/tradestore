# tradestore


#API List:
#Get API to get all the trade details:
http://localhost:8080/trade/get/all

#Post API to add new trade or update existing one
http://localhost:8080/trade/add
#Payload
{
    "tradeId": "T11",
    "version": 6,
    "countryPartyId": "CP-1",
    "bookId": "B5",
    "maturityDate": "2022-08-23T10:02:31.240+00:00",
    "creationDate": "2022-07-23T10:02:31.240+00:00",
    "expired": "N"
}


#Build:
mvn clean install

#Run time:
Java 11 or above
