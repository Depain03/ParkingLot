# ParkingLot

# Admin API's (Search)

1. URI: /parking/_search/{color}/registration

   Method: GET
   
2. URI: /parking/_search/{registration}/slot

   Method: GET

3. URI: /parking/_search/{color}/slots

   Method: GET   
   
# User API's

1. URI: /parking

   Method: GET
   
2. URI: /parking

   Method: POST
   
   Body: {
	"registration": "",
	"colour": "",
	"slot": 0,
	"level": 0
  }

3. URI: /parking

   Method: Delete  
   
   Body: {
	"registration": ""
}
