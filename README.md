# ParkingLot

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
