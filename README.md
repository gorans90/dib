# dib
Dib task

This task has been done using following tech stack: Java 8, Spring Boot, Hibernate, H2 database (I use H2 
 because this is for testing purpose, for production it should be changed to some else db, MSSQL, MySql etc)
I selected these tech stack because I feel most comfortable with it.

Start project with command:
`mvn spring-boot:run` 

Once project is started you can find swagger on the following link:
http://localhost:8078/dib/swagger-ui.html

CURL command for init data, but I also want to notice that initialization has been added on project startup:
 * curl -X GET "http://localhost:8078/dib/beer/init" -H "accept: application/json"

CURL command for getting beer by id:
 * curl -X GET "http://localhost:8078/dib/beer/1" -H "accept: application/json"
 * Response will be in this format:
 ~~~~
  {
     "id": 1,
     "name": "Declassified Demi-God",
     "description": "An exclusive barrel-aged blend, brought together to reward investors on Equity for Punks USA; two imperial stouts aged in grain whisky barrels for over a year.",
     "mash_temp": 65
  }
 ~~~~

CURL command for getting all:
 * curl -X GET "http://localhost:8078/dib/beer/all" -H "accept: application/json"
 * Response will be array:
 ~~~~
 [
   {
     "id": 1,
     "name": "Declassified Demi-God",
     "description": "An exclusive barrel-aged blend, brought together to reward investors on Equity for Punks USA; two imperial stouts aged in grain whisky barrels for over a year.",
     "mash_temp": 65
   },
   {
     "id": 2,
     "name": "New England IPA",
     "description": "BrewDog vs Cloudwater is a 6.8% Vermont-style India Pale Ale, hopped with Mosaic in the whirlpool only. Without any big- hitting additions of hops in the boil, we carried the flavour by heavily dry-hopping the brew with Citra and Mosaic. We also used oats in the recipe to deliver the trademark smooth mouthfeel – and made it truly authentic by propagating a one-off culture of Vermont yeast, specifically for the brew.",
     "mash_temp": 68
   }
  ]
 ~~~~
CURL for delete by id:
 * curl -X DELETE "http://localhost:8078/dib/beer/delete/1" -H "accept: */*"
 * Response will be message: "Beer has been successfully deleted"

Run tests with command:
`mvn test`