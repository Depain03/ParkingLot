# ParkingLot

There are two types of users:

1. The customers who want to park their cars.

2. The admin of the parking lot who can do the searches.


# Admin API's (Search)

1. URI: /api/parking/_search/{color}/registration

   Method: GET
   
2. URI: /api/parking/_search/{registration}/slot

   Method: GET

3. URI: /api/parking/_search/{color}/slots

   Method: GET   
   
   
# User API's

1. URI: /api/parking

   Method: GET
   
2. URI: /api/parking

   Method: POST
   
   Body: {
	"registration": "",
	"colour": "",
	"slot": 0,
	"level": 0
  }

3. URI: /api/parking

   Method: Delete  
   
   Body: {
	"registration": ""
}


# Swagger UI

Swagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the API’s resources without having any of the implementation logic in place. It’s automatically generated from your Swagger specification, with the visual documentation making it easy for back end implementation and client side consumption.

URI: /swagger-ui.html
